package c.example.weatherapp.screens.mainScreen.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
/**
 * adapts the data for ImageView
 */
@BindingAdapter("android:src")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.setBackgroundResource(GetDraw.getDraw(url))
}