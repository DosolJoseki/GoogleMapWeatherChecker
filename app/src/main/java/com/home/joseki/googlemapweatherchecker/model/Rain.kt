package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    private val oneHour: String
)
