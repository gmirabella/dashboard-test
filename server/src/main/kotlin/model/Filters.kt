package com.project.dashboard.model


data class Filters(
        val countries: List<Country>,
        val dayParts: List<DayPart>
)

data class Country(
        val isoCode: String,
        val name: String
)

enum class DayPart {
    NIGHT,     // Night     00 - 06
    MORNING,   // Morning   06 - 12
    AFTERNOON, // Afternoon 12 - 18
    EVENING    // Evening   18 - 24
}