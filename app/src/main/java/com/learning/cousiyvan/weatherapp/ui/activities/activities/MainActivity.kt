package com.learning.cousiyvan.weatherapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.learning.cousiyvan.weatherapp.R
import com.learning.cousiyvan.weatherapp.adapters.ForecastListAdapter
import com.learning.cousiyvan.weatherapp.domain.commands.RequestForecastCommand
import com.learning.cousiyvan.weatherapp.ui.utils.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val items = listOf(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 31/17",
        "Wed 6/25 - Cloudy - 31/17",
        "Thur 6/26 - Rainy - 31/17",
        "Fri 6/27 - Foggy - 31/17",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 31/17",
        "Sun 6/29 - Sunny - 31/17"
    )

    private var zipCode: Long by DelegatesExt.preference(this,
        SettingsActivity.ZIP_CODE,
        SettingsActivity.DEFAULT_ZIP
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        // val forecastList = findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this)
        // forecastList.adapter = ForecastListAdapter(items)
        attachToScroll(forecastList)

        loadForecast()
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync() {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            val adapter = ForecastListAdapter(result, {
                // toast(it.description)
                startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
            })
            forecastList.adapter = adapter
            toolbarTitle = "${result.city} (${result.country})"
        }
    }
}
