package com.example.weatherapp.screens.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseMVPActivity
import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.base.BaseView
import com.example.weatherapp.base.Router

class MVPActivity : BaseMVPActivity<BasePresenter<BaseView, Router>, RouterMVP>() {
    override fun provideLayoutId(): Int {
        TODO("Not yet implemented")
    }

    override fun providePresenter(): BasePresenter<BaseView, Router> {

        return PresenterMVP()//в таком случае не могу на вход послать этот презентер, хотя он и наследник BasePresenter<ViewMVP, RouterMVP>
    }

    override fun provideRouter(): RouterMVP {
        TODO("Not yet implemented")
    }

}