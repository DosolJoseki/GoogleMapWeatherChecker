package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    private val all: String = ""
)