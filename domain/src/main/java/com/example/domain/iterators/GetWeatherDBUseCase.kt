package com.example.domain.iterators

import com.example.domain.executor.PostExecutionThread
import com.example.domain.repostitory.WeatherRepository
import javax.inject.Inject
/**
 * receiving data from the db
 */
class GetWeatherDBUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    val repository: WeatherRepository
) : BaseUseCase(postExecutionThread) {

    fun getWeatherDB() = repository
        .getWeatherDB()
        .observeOn(postExecutionThreadScheduler)
}