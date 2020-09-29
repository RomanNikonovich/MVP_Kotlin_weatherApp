package com.example.domain.iterators

import com.example.domain.entity.ForecastBase
import com.example.domain.entity.WeatherBase
import com.example.domain.executor.PostExecutionThread
import com.example.domain.repostitory.WeatherRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
/**
 * receiving data from the server
 */
class GetWeatherUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    val repository: WeatherRepository
) : BaseUseCase(postExecutionThread) {

    fun getWeather(
        lat: Double,
        lon: Double,
        id: String
    ) = repository
        .getWeather(lat, lon, id)
        .subscribeOn(threadExecutionScheduler)
        .observeOn(postExecutionThreadScheduler)
}

