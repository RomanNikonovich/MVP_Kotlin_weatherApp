package com.example.domain.repostitory

import com.example.domain.entity.ForecastBase
import com.example.domain.entity.ForecastEnt
import com.example.domain.entity.WeatherBase
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

interface WeatherRepository {

    fun getWeather(lat: Double, lon: Double, id: String) : Observable<ForecastEnt>

    fun getWeatherDB() : Flowable<out ForecastBase<out WeatherBase>>
}