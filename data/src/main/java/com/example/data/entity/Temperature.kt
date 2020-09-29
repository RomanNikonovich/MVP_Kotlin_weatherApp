package com.example.data.entity

import com.google.gson.annotations.SerializedName

class Temperature(
    @SerializedName("day") val day: Double,
    @SerializedName("night") val night: Double
) {
}