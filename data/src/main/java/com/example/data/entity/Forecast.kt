package com.example.data.entity

import com.google.gson.annotations.SerializedName

class Forecast(
    @SerializedName("city")
    val city: City,
    @SerializedName("list")
    val list: MutableList<Info>
) {
}