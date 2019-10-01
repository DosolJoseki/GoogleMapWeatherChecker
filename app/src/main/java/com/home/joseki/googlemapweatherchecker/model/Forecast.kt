package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("city")
    var city: City? = null,
    @SerializedName("cnt")
    var cnt: String = "",
    @SerializedName("cod")
    var cod: String = "",
    @SerializedName("message")
    var message: String = "",
    @SerializedName("list")
    var list: List<ForecastItem> = ArrayList()
)