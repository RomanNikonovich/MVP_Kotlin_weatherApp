package com.example.weatherapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseMVPActivity<Presenter : BasePresenter<BaseView, Router>, R : Router> :
    AppCompatActivity(), BaseView {

    lateinit var presenter: Presenter
    private var router: R? = null

    abstract fun provideLayoutId(): Int

    abstract fun providePresenter(): Presenter

    abstract fun provideRouter(): R

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = providePresenter()
        setContentView(provideLayoutId())

        router = provideRouter()
        presenter.attach(this, router!!)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        router = null
        presenter.detach()
        presenter.onDestroy()
    }

    override fun showProgress() {}

    override fun dismissProgress() {}

    override fun showError(throwable : Throwable){}

}