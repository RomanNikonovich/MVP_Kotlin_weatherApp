package c.example.weatherapp.app

import android.app.Application
import c.example.weatherapp.injection.AppComponent
import c.example.weatherapp.injection.AppModule
import c.example.weatherapp.injection.DaggerAppComponent
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