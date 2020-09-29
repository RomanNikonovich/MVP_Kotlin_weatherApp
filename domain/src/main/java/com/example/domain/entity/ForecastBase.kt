package com.example.domain.entity

abstract class ForecastBase<T : WeatherBase>(
    val cityB: String,
    val countryB: String,
    val listB: List<T>
)