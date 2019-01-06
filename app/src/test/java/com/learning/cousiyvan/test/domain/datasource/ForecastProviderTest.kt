package com.learning.cousiyvan.test.domain.datasource

import com.learning.cousiyvan.weatherapp.data.server.ForecastServer
import com.learning.cousiyvan.weatherapp.domain.datasource.ForecastDataSource
import com.learning.cousiyvan.weatherapp.domain.datasource.ForecastProvider
import com.learning.cousiyvan.weatherapp.domain.model.Forecast
import com.learning.cousiyvan.weatherapp.domain.model.ForecastList
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.then
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ForecastProviderTest {
    @Test
    fun `data source returns a value`() {
        val ds = mock(ForecastDataSource::class.java)
        `when`(ds.requestDayForecast(0)).then {
                Forecast(0, 0, "desc", 20, 0, "url")
            }

        val provider = ForecastProvider(listOf(ds))
        Assert.assertNotNull(provider.requestForecast(0))
    }

    @Test
    fun `empty database returns server value`() {
        val db = mock(ForecastDataSource::class.java)

        val server = mock(ForecastServer::class.java)
        `when`(server.requestForecastByZipCode(any(Long::class.java), any(Long::class.java)))
            .then  {
                ForecastList(0, "city", "country", listOf())
            }

        val provider = ForecastProvider(listOf(db, server))

        Assert.assertNotNull(provider.requestByZipCode(0, 0))
    }
}