package com.example.domain.entity

data class WeatherEnt(
    val description: String,
    val icon: String,
    val date: String,
    val dayTemper: String,
    val nightTemper: String
) : WeatherBase(date, dayTemper, nightTemper)