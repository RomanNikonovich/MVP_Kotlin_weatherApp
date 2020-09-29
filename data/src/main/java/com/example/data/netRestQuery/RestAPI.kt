package com.example.data.netRestQuery

import com.example.data.entity.Forecast
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAPI {

    @GET("forecast/daily")
    fun loadWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("cnt") count: Int,
        @Query("lang") lang: String,
        @Query("appid") appid: String
    ) : Observable<Forecast>
}