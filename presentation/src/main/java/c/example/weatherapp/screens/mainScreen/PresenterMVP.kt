package c.example.weatherapp.screens.mainScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.databinding.ObservableField
import com.example.domain.entity.ForecastEnt

import com.example.domain.iterators.GetWeatherDBUseCase
import com.example.domain.iterators.GetWeatherUseCase
import com.example.weatherapp.R
import c.example.weatherapp.app.App
import c.example.weatherapp.base.BasePresenter
import c.example.weatherapp.base.BaseView
import c.example.weatherapp.base.Router
import com.google.android.gms.location.FusedLocationProviderClient
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

const val ID = "c0c4a4b4047b97ebc5948ac9c48c0559"

class PresenterMVP : BasePresenter<BaseView, Router>() {
    val dateNow: ObservableField<String> = ObservableField()
    val country: ObservableField<String> = ObservableField()
    val city: ObservableField<String> = ObservableField()
    val tempNow: ObservableField<String> = ObservableField()
    val iconNow: ObservableField<String> = ObservableField()
    val date_1: ObservableField<String> = ObservableField()
    val icon_1: ObservableField<String> = ObservableField()
    val temp_1: ObservableField<String> = ObservableField()
    val date_2: ObservableField<String> = ObservableField()
    val icon_2: ObservableField<String> = ObservableField()
    val temp_2: ObservableField<String> = ObservableField()
    val date_3: ObservableField<String> = ObservableField()
    val icon_3: ObservableField<String> = ObservableField()
    val temp_3: ObservableField<String> = ObservableField()


    @Inject
    lateinit var dbUseCase: GetWeatherDBUseCase

    @Inject
    lateinit var weatherUseCase: GetWeatherUseCase

    @Inject
    lateinit var providerClient: FusedLocationProviderClient


    override fun onCreate() {
        super.onCreate()
        App.appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        if ((view as ViewMVP).isGranted()) getLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        providerClient
            .lastLocation
            ?.addOnSuccessListener { getWeather(it.latitude, it.longitude) }
    }

    /**
     * getting data from the server
     */
    private fun getWeather(lat: Double, lon: Double) {
        compositeDisposable.add(
            weatherUseCase
                .getWeather(lat, lon, ID)
                .subscribe({
                    setData(it)
                }, {
                    view?.showError(error(it))
                })
        )

    }

    private fun setData(forecastEnt: ForecastEnt) {
        val w = forecastEnt.list[0]
        val w1 = forecastEnt.list[1]
        val w2 = forecastEnt.list[2]
        val w3 = forecastEnt.list[3]

        dateNow.set(w.date)
        country.set(forecastEnt.country)
        city.set(forecastEnt.city)
        tempNow.set("${w.description} ${w.dayTemper}")
        iconNow.set(w.icon)
        date_1.set(w1.date)
        icon_1.set(w1.icon)
        temp_1.set("${w1.description}\n${w1.dayTemper}")
        date_2.set(w2.date)
        icon_2.set(w2.icon)
        temp_2.set("${w2.description}\n${w2.dayTemper}")
        date_3.set(w3.date)
        icon_3.set(w3.icon)
        temp_3.set("${w3.description}\n${w3.dayTemper} ")

    }

    /**
     * catches errors from the server
     * if no internet or server not available then try get data from db - call loadDataDB()
     */
    private fun error(throwable: Throwable): String {
        return when (throwable) {
            is SocketTimeoutException -> {
                loadDataDB(); router?.activity?.resources?.getText(R.string.server_not_available)
                    .toString()
            }
            is IOException -> {
                loadDataDB(); router?.activity?.resources?.getText(R.string.no_internet).toString()
            }
            is HttpException -> {
                loadDataDB(); throwable.response()?.body() as String
            }
            else -> router?.activity?.resources?.getText(R.string.error).toString()
        }

    }

    private fun loadDataDB() {
        compositeDisposable.add(dbUseCase.getWeatherDB().subscribe { setData(it as ForecastEnt) })
    }
}