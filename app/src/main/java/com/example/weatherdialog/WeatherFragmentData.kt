package com.example.weatherdialog

data class WeatherFragmentData(
    val city_name: String,
    val current_date: String,
    val weather_icon: Int,
    val day_temp: String,
    val feels_like: String,
    val pressure: String,
    val wind_speed: String,
    val humidity: String
)

data class Vision (
    val progressBar: Int,
    val wrappers: Int,
)