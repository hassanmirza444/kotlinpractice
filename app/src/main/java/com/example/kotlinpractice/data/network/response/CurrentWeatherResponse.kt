package com.example.kotlinpractice.data.network.response

import com.example.kotlinpractice.data.db.entity.CurrentWeatherEntry
import com.example.kotlinpractice.data.db.entity.Location
import com.example.kotlinpractice.data.db.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)