package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    var temp: String = "",
    @SerializedName("temp_min")
    var tempMin: String = "",
    @SerializedName("humidity")
    var humidity: String = "",
    @SerializedName("pressure")
    var pressure: String = "",
    @SerializedName("temp_max")
    var tempMax: String = ""
)