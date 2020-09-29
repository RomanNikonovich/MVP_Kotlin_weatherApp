package c.example.weatherapp.base

interface BaseView {

    fun showProgress()

    fun dismissProgress()

    fun showError(error: String)
}