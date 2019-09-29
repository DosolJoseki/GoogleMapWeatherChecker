package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("deg")
    private val deg: String = "",
    @SerializedName("speed")
    private val speed: String = "",
    @SerializedName("gust")
    private val gust: String = ""
)