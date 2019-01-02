package com.learning.cousiyvan.weatherapp.data.server

import com.antonioleiva.weatherapp.data.server.ServerDataMapper
import com.learning.cousiyvan.weatherapp.data.db.ForecastDb
import com.learning.cousiyvan.weatherapp.domain.datasource.ForecastDataSource
import com.learning.cousiyvan.weatherapp.domain.model.Forecast
import com.learning.cousiyvan.weatherapp.domain.model.ForecastList
import java.lang.UnsupportedOperationException


class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(), private val forecastDb: ForecastDb = ForecastDb()): ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? {
        throw UnsupportedOperationException()
    }
}