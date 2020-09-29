package com.example.data.entity

import com.google.gson.annotations.SerializedName
import java.util.*

class Info(
    @SerializedName("dt") val dt: Calendar,
    @SerializedName("temp") val temp: Temperature,
    @SerializedName("weather") val weather: List<MoreTemp>
)