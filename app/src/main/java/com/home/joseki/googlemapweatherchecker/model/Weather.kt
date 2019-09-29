package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("icon")
    private val icon: String = "",
    @SerializedName("description")
    private val description: String = "",
    @SerializedName("main")
    private val main: String = "",
    @SerializedName("id")
    private val id: String = ""
)