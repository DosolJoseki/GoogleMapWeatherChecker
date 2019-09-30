package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class ForecastList(
    @SerializedName("dt")
    var dt: String = "",
    @SerializedName("rain")
    var rain: Rain? = null,
    @SerializedName("dt_txt")
    var dtTxt: String = "",
    @SerializedName("weather")
    var weather: List<Weather>? = null,
    @SerializedName("main")
    var main: Main? = null,
    @SerializedName("clouds")
    var clouds: Clouds? = null,
    @SerializedName("sys")
    var sys: Sys? = null,
    @SerializedName("wind")
    var wind: Wind? = null
)