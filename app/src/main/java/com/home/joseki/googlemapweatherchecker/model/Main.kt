package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    var temp: Double = 0.0,
    @SerializedName("temp_min")
    var tempMin: String = "",
    @SerializedName("humidity")
    var humidity: String = "",
    @SerializedName("pressure")
    var pressure: String = "",
    @SerializedName("temp_max")
    var tempMax: String = "",
    @SerializedName("grnd_level")
    val grndLevel: String = "",
    @SerializedName("temp_kf")
    val tempKf: String = "",
    @SerializedName("sea_level")
    val seaLevel: String = ""
)