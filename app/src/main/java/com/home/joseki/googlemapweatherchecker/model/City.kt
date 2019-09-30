package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("country")
    var country: String = "",
    @SerializedName("coord")
    var coord: Coord? = null,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("id")
    var id: String = "",
    @SerializedName("sys")
    var sys: Sys? = null,
    @SerializedName("population")
    var population: String = ""
)