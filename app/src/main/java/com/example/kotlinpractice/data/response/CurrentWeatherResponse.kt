package com.example.kotlinpractice.data.response


data class CurrentWeatherResponse(
    val current: Current,
    val location: Location,
    val request: Request
)