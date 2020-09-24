package com.example.weatherapp.app

import android.app.Application
import com.example.weatherapp.injection.AppComponent
import com.example.weatherapp.injection.AppModule
import com.example.weatherapp.injection.DaggerAppComponent
import io.realm.Realm

class App : Application() {

    companion object{
        lateinit var appComponent : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        Realm.init(this)
    }
}