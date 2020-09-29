package com.example.data.entity

import com.google.gson.annotations.SerializedName

class City(
    @SerializedName("name") val cityName: String,
    @SerializedName("country") val country: String
)