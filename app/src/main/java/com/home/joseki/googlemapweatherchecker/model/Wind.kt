package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("deg")
    val deg: String = "",
    @SerializedName("speed")
    val speed: String = "",
    @SerializedName("gust")
    val gust: String = ""
)