package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    private val country: String = "",
    @SerializedName("sunrise")
    private val sunrise: String = "",
    @SerializedName("sunset")
    private val sunset: String = "",
    @SerializedName("message")
    private val message: String = ""
)