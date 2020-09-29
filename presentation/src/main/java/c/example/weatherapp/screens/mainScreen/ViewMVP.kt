package c.example.weatherapp.screens.mainScreen

import android.widget.Toast
import c.example.weatherapp.base.BaseView

interface ViewMVP : BaseView {
    fun isGranted(): Boolean

}