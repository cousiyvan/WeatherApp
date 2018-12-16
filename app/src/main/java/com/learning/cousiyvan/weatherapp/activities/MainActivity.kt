package com.learning.cousiyvan.weatherapp.activities

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.learning.cousiyvan.weatherapp.R
import com.learning.cousiyvan.weatherapp.adapters.ForecastListAdapter
import com.learning.cousiyvan.weatherapp.data.Request
import com.learning.cousiyvan.weatherapp.domain.commands.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    private val items = listOf(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 31/17",
        "Wed 6/25 - Cloudy - 31/17",
        "Thur 6/26 - Rainy - 31/17",
        "Fri 6/27 - Foggy - 31/17",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 31/17",
        "Sun 6/29 - Sunny - 31/17"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this)
        // forecastList.adapter = ForecastListAdapter(items)

        doAsync() {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) }
        }
    }
}
