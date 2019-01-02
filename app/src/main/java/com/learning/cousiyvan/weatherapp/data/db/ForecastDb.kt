package com.learning.cousiyvan.weatherapp.data.db

import android.text.method.TextKeyListener.clear
import com.learning.cousiyvan.weatherapp.data.db.utils.ForecastDbHelper
import com.learning.cousiyvan.weatherapp.domain.datasource.ForecastDataSource
import com.learning.cousiyvan.weatherapp.domain.model.Forecast
import com.learning.cousiyvan.weatherapp.domain.model.ForecastList
import com.learning.cousiyvan.weatherapp.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance, private val dataMapper: DbDataMapper = DbDataMapper()) :
    ForecastDataSource {
    // private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance
    // private val dataMapper: DbDataMapper = DbDataMapper()

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = {id} AND ${DayForecastTable.DATE} >= {date}"
        val dailyForecast = select(DayForecastTable.NAME).
            where(dailyRequest, "id" to zipCode, "date" to date).
            parseList { DayForecast(HashMap(it))}

        val city = select(CityForecastTable.NAME)
            .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
            .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME)
            .byId(id)
            .parseOpt { DayForecast(HashMap(it)) }

        if (forecast != null)
            dataMapper.convertDayToDomain(forecast)
        else
            null
    }



    fun saveForecast(forecast:ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}