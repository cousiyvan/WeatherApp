package com.learning.cousiyvan.weatherapp.domain.datasource

import com.learning.cousiyvan.weatherapp.domain.model.Forecast
import com.learning.cousiyvan.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}