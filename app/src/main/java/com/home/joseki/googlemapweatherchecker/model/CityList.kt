package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class CityList(
    @SerializedName("Cities")
    val Cities: List<CityInfo> = emptyList()
)