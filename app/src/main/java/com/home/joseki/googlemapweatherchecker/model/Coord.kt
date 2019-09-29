package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lon")
    private val lon: String = "",
    @SerializedName("lat")
    private val lat: String = ""
)