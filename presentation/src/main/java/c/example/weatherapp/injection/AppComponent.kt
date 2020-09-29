package c.example.weatherapp.injection

import c.example.weatherapp.base.BasePresenter
import c.example.weatherapp.base.BaseView
import c.example.weatherapp.base.Router
import c.example.weatherapp.screens.mainScreen.MVPActivity
import c.example.weatherapp.screens.mainScreen.PresenterMVP
import c.example.weatherapp.screens.mainScreen.RouterMVP
import c.example.weatherapp.screens.mainScreen.ViewMVP
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun  inject(presenterMVP: PresenterMVP)

}