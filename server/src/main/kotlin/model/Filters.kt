package com.project.dashboard.model


data class Filters(
        val countries: List<Country>,
        val dayParts: List<DayPart>
)

data class Country(
        val isoCode: String,
        val name: String
)

enum class DayPart (val displayName: String) {
    DAY_PART_0("Night"),     // Night     00 - 06
    DAY_PART_1("Morning"),   // Morning   06 - 12
    DAY_PART_2("Afternoon"), // Afternoon 12 - 18
    DAY_PART_3("Evening")    // Evening   18 - 24
}