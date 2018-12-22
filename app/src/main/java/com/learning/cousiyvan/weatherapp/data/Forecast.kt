package com.learning.cousiyvan.weatherapp.data

data class Forecast(val dt: Long, val temp: Float, val temp_max: Float, val temp_min: Float, val pressure: Float, val humidity: Int, val weather: List<Weather>, val speed: Float, val deg: Int, val clouds: Clouds, val rain: Rain) {
}