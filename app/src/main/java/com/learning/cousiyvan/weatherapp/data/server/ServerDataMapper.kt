package com.antonioleiva.weatherapp.data.server

import com.learning.cousiyvan.weatherapp.data.Forecast
import com.learning.cousiyvan.weatherapp.data.ForecastResult
import com.learning.cousiyvan.weatherapp.domain.model.Forecast as ModelForecast
import java.util.*
import java.util.concurrent.TimeUnit

class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        com.learning.cousiyvan.weatherapp.domain.model.ForecastList(
            zipCode,
            city.name,
            city.country,
            convertForecastListToDomain(list)
        )
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt, weather[0].description, temp_max.toInt(), temp_min.toInt(),
            generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}