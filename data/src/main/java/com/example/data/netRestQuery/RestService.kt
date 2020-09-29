package com.example.data.netRestQuery

import javax.inject.Inject


class RestService @Inject constructor(val restAPI: RestAPI) {

    companion object {
        val UNIT = "metric"
        val COUNT = 4
        val LANG = "en"
    }

    fun loadWeather(lat: Double, lon: Double, id: String) =
        restAPI
            .loadWeather(lat, lon, UNIT, COUNT, LANG, id)
}