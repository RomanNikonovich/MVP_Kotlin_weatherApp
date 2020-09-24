package com.example.weatherapp.base

interface BaseView {

    fun showProgress()

    fun dismissProgress()

    fun showError(throwable : Throwable)
}