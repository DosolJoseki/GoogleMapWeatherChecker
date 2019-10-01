package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    val country: String = "",
    @SerializedName("sunrise")
    val sunrise: String = "",
    @SerializedName("sunset")
    val sunset: String = "",
    @SerializedName("message")
    val message: String = ""
)