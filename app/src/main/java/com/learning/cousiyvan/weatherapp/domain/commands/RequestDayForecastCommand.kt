package com.learning.cousiyvan.weatherapp.domain.commands

import com.learning.cousiyvan.weatherapp.domain.datasource.ForecastProvider
import com.learning.cousiyvan.weatherapp.domain.model.Forecast

class RequestDayForecastCommand(private val id: Long, private val forecastProvider: ForecastProvider = ForecastProvider()): Command<Forecast> {
    override fun execute(): Forecast = forecastProvider.requestForecast(id)
}