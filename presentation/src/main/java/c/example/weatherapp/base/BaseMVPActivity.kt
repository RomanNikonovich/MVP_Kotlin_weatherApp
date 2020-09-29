package c.example.weatherapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.weatherapp.BR

abstract class BaseMVPActivity<Binding : ViewDataBinding, Presenter : BasePresenter<BaseView, Router>, R : Router> :
    AppCompatActivity(), BaseView {

    private lateinit var binding: ViewDataBinding
    lateinit var presenter: Presenter
    private var router: R? = null

    abstract fun provideLayoutId(): Int

    abstract fun providePresenter(): Presenter

    abstract fun provideRouter(): R

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = providePresenter()
        binding = DataBindingUtil.setContentView(this, provideLayoutId())
        binding.setVariable(BR.presenter, presenter)
        router = provideRouter()
        presenter.attach(this, router!!)
        presenter.onCreate()
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

    override fun showError(error: String) {}

}