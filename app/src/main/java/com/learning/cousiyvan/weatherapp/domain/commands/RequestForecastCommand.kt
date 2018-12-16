package com.learning.cousiyvan.weatherapp.domain.commands

import com.learning.cousiyvan.weatherapp.data.ForecastRequest
import com.learning.cousiyvan.weatherapp.domain.mappers.ForecastDataMapper
import com.learning.cousiyvan.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}