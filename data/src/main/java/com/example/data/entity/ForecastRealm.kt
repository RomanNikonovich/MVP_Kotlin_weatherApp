package com.example.data.entity

import io.realm.RealmObject

open class ForecastRealm(
    var city: String = "",
    var country: String = "",
    var date: String = "",
    var dayTemper: String = "",
    var nightTemper: String = "",
    var description: String = "",
    var icon: String = ""
) : RealmObject() {
}