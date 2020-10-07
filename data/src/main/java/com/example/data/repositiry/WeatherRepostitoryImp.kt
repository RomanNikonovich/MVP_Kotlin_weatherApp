package com.example.data.repositiry

import com.example.data.entity.ForecastRealm
import com.example.data.entity.Info
import com.example.data.netRestQuery.RestService
import com.example.domain.entity.ForecastBase
import com.example.domain.entity.ForecastEnt
import com.example.domain.entity.WeatherBase
import com.example.domain.entity.WeatherEnt
import com.example.domain.repostitory.WeatherRepository
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableOnSubscribe
import io.reactivex.rxjava3.core.Observable
import io.realm.Realm
import io.realm.kotlin.where
import java.util.*
import javax.inject.Inject

class WeatherRepostitoryImp @Inject constructor(val restService: RestService) : WeatherRepository {

    override fun getWeather(
        lat: Double,
        lon: Double,
        id: String
    ): Observable<ForecastEnt> {
        return restService
            .loadWeather(lat, lon, id)
            .map {
                it.list.map { inf: Info ->

                    ForecastRealm(
                        it.city.cityName,
                        it.city.country,
                        "${
                            if (inf.dt.get(Calendar.DAY_OF_MONTH) in 1..9) buildString {
                                append(0); append(
                                inf.dt.get(Calendar.DAY_OF_MONTH)
                            )
                            } else inf.dt.get(Calendar.DAY_OF_MONTH)
                        }.${
                            if ((inf.dt.get(Calendar.MONTH) + 1) in 1..9) buildString {
                                append(
                                    0
                                ); append(inf.dt.get(Calendar.MONTH) + 1)
                            } else inf.dt.get(Calendar.MONTH) + 1
                        }",
                        inf.temp.day.toInt().toString(),
                        inf.temp.night.toInt().toString(),
                        inf.weather[0].description,
                        inf.weather[0].icon
                    )
                }

            }

            .doOnNext {
                Realm.getDefaultInstance().use { realmInstance ->
                    realmInstance.executeTransactionAsync { realm ->
                        realm
                            .where<ForecastRealm>()
                            .findAll()
                            .deleteAllFromRealm()

                        realm.insert(it)
                    }
                }
            }
            .map { forecast ->
                ForecastEnt(
                    forecast[0].city,
                    forecast[0].country,
                    forecast.map {
                        WeatherEnt(
                            it.description,
                            it.icon,
                            it.date,
                            it.dayTemper,
                            it.nightTemper
                        )
                    })
            }
    }

    /**Т.к. Realm не поддерживает Rx3, а проект написан  на Rx3 и не могу написать .asFlowable(),
     * то приходится изобретать велосипед и создавать вручну Observable*/
    override fun getWeatherDB(): Flowable<ForecastEnt> {
        var realm: Realm? = null
        val forecastList: List<ForecastEnt> = try {
            realm = Realm.getDefaultInstance()
            realm
                .where<ForecastRealm>()
                .findAllAsync()
                .map {
                    ForecastEnt(
                        it.city,
                        it.country,
                        listOf(
                            WeatherEnt(
                                it.description,
                                it.icon,
                                it.date,
                                it.dayTemper,
                                it.nightTemper
                            )
                        )
                    )
                }

        } catch (er: IllegalStateException) {
            mutableListOf<ForecastEnt>()
        } finally {
            realm?.close()
        }

        return Flowable.create(
            FlowableOnSubscribe { emitter ->
                val forecastEnt: ForecastEnt = ForecastEnt(
                    forecastList[0].city,
                    forecastList[0].country,
                    forecastList.flatMap { it.list }); emitter.onNext(forecastEnt); emitter.onComplete()
            },
            BackpressureStrategy.BUFFER
        )
    }


}