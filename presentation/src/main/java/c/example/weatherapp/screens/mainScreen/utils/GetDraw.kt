package c.example.weatherapp.screens.mainScreen.utils

import com.example.weatherapp.R
/**
 * depending on the data from the server, finds drawable from res
 *
 */
object GetDraw {
    /**
     * @param id - comes from the server
     */
    fun getDraw(id: String?) =
        when (id) {
            null -> R.drawable.c
            "01d" -> R.drawable.a
            "01n" -> R.drawable.b
            "02d" -> R.drawable.c
            "02n" -> R.drawable.d
            "03d" -> R.drawable.e
            "03n" -> R.drawable.f
            "04d" -> R.drawable.g
            "04n" -> R.drawable.h
            "09d" -> R.drawable.i
            "09n" -> R.drawable.j
            "10d" -> R.drawable.k
            "10n" -> R.drawable.l
            "11d" -> R.drawable.m
            "11n" -> R.drawable.n
            "13d" -> R.drawable.o
            "13n" -> R.drawable.p
            "50d" -> R.drawable.q
            "50n" -> R.drawable.r
            else -> R.drawable.c
        }

}