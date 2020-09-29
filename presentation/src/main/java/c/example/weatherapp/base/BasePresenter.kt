package c.example.weatherapp.base

import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BasePresenter<View : BaseView, R : Router> {

    protected val compositeDisposable = CompositeDisposable()

    protected var view: View? = null
    protected var router: R? = null

    fun attach(view: View, router: R) {
        this.view = view
        this.router = router
    }

    fun detach() {
        this.view = null
        this.router = null
    }

    open fun onCreate() {}

    open fun onResume() {
    }

    open fun onPause() {
    }

    open fun onStart() {
    }

    open fun onStop() {
    }

    open fun onDestroy() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}