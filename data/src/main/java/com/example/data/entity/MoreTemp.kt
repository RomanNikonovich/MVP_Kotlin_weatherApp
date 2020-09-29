package com.example.data.entity

import com.google.gson.annotations.SerializedName

class MoreTemp(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)