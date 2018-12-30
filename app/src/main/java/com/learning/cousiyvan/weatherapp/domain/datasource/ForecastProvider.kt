package com.learning.cousiyvan.weatherapp.domain.datasource

import com.learning.cousiyvan.weatherapp.data.db.ForecastDb
import com.learning.cousiyvan.weatherapp.data.server.ForecastServer
import com.learning.cousiyvan.weatherapp.domain.model.ForecastList
import com.learning.cousiyvan.weatherapp.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList =
        sources.firstResult { requestSource(it, days, zipCode) }

    fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }
}