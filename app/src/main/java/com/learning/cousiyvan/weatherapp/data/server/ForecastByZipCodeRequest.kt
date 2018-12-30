package com.learning.cousiyvan.weatherapp.data.server

import com.google.gson.Gson
import com.learning.cousiyvan.weatherapp.data.Forecast
import com.learning.cousiyvan.weatherapp.data.ForecastResult
import java.net.URL

class ForecastByZipCodeRequest(private val zipCode: Long, val gson: Gson = Gson()) {
    companion object {
        private const val APP_ID = "2932a2c15493d0e978b8415b17c6ee3e"
        private const val URL = "https://api.openweathermap.org/data/2.5/forecast?daily&mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}