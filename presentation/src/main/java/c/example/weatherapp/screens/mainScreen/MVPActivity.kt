package c.example.weatherapp.screens.mainScreen

import android.annotation.SuppressLint
import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.domain.iterators.GetWeatherUseCase
import com.example.weatherapp.R
import c.example.weatherapp.app.App
import c.example.weatherapp.base.BaseMVPActivity
import c.example.weatherapp.base.BasePresenter
import c.example.weatherapp.base.BaseView
import c.example.weatherapp.base.Router
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tbruyelle.rxpermissions2.RxPermissions
import javax.inject.Inject

class MVPActivity : BaseMVPActivity<ActivityMainBinding, PresenterMVP, RouterMVP>(), ViewMVP {
    override fun provideLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun providePresenter(): PresenterMVP {
        return PresenterMVP()
    }

    override fun provideRouter(): RouterMVP {
        return RouterMVP(this)
    }

    /**
     * check user's permission to track locations
     * if yes - try to load data from server
     * no - user getting msg "There is no permission"
     */
    override fun isGranted(): Boolean {
        var isGranted = false
        RxPermissions(this)
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .subscribe {
                isGranted = it; if (!it) Toast.makeText(
                this,
                this.getText(R.string.permission),
                Toast.LENGTH_LONG
            ).show()
            }
        return isGranted
    }

    override fun showError(error: String) {
        Toast.makeText(
            this,
            error,
            Toast.LENGTH_LONG
        ).show()
    }

}