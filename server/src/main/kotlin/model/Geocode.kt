package com.project.dashboard.model

data class Geocode(
        val address : GeocodeCountry
)

data class GeocodeCountry(
        val country_code: String
)
