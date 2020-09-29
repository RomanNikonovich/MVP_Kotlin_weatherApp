package c.example.weatherapp.injection

import android.content.Context
import androidx.annotation.UiThread
import com.example.data.netRestQuery.RestAPI
import com.example.data.netRestQuery.RestService
import com.example.data.repositiry.WeatherRepostitoryImp
import com.example.domain.executor.PostExecutionThread
import com.example.domain.repostitory.WeatherRepository
import c.example.weatherapp.executors.UIThread
import c.example.weatherapp.screens.mainScreen.utils.DateJsonConverter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.*
import javax.inject.Singleton

const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun getContextMod() = context

    @Provides
    @Singleton
    fun getUiThread(): PostExecutionThread {
        return UIThread()
    }

    @Provides
    @Singleton
    fun getRepository(restService: RestService): WeatherRepository {
        return WeatherRepostitoryImp(restService)
    }

    @Provides
    @Singleton
    fun getRestApi(retrofit: Retrofit): RestAPI {
        return retrofit.create(RestAPI::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Calendar::class.java, DateJsonConverter())
            .create()

    }

    @Provides
    fun getProviderClient(): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)

    }
}