package com.example.domain.entity

data class ForecastEnt(
    val city: String,
    val country: String,
    val list: List<WeatherEnt>
) : ForecastBase<WeatherBase>(city, country, list) {
}