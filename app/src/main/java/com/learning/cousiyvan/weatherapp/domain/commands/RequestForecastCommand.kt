package com.learning.cousiyvan.weatherapp.domain.commands

import com.learning.cousiyvan.weatherapp.data.ForecastRequest
import com.learning.cousiyvan.weatherapp.domain.datasource.ForecastProvider
import com.learning.cousiyvan.weatherapp.domain.mappers.ForecastDataMapper
import com.learning.cousiyvan.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long, private val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {
    companion object {
        const val DAYS = 7
    }

    override fun execute(): ForecastList =
        // val forecastRequest = ForecastRequest(zipCode)
        // return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
        forecastProvider.requestByZipCode(zipCode, DAYS)
}