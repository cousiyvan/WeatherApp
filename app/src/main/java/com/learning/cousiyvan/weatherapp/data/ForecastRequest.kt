package com.learning.cousiyvan.weatherapp.data

import android.util.Log
import com.google.gson.Gson
import java.net.URL

class ForecastRequest(private val zipCode: String) {
    companion object {
        private const val APP_ID = "2932a2c15493d0e978b8415b17c6ee3e"
        private const val URL = "https://api.openweathermap.org/data/2.5/forecast?daily&mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }

    fun execute(): ForecastResult {
        try {
            val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
            Log.d(javaClass.simpleName, "forecast json: $forecastJsonStr")
            return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
        } catch (exc: Exception) {
            Log.e(javaClass.simpleName, "Error: ${exc.printStackTrace()}");
            return Gson().fromJson("", ForecastResult::class.java)
        }

    }
}