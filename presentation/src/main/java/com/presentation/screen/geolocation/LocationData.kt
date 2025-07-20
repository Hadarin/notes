package com.presentation.screen.geolocation

import kotlinx.serialization.Serializable

@Serializable
data class LocationData(
    val lat: Double,
    val lng: Double
)