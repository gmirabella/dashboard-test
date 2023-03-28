package com.project.dashboard.model


data class Filters(
        val countries: List<Country>,
        val dayParts: List<DayPart>,
        val periodDays: List<PeriodDays>
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

enum class PeriodDays (val days: Long) {
    LAST_WEEK  (7),
    LAST_MONTH (30),
    LAST_YEAR  (365);
}
enum class InterestType(val desc: String) {
    MUSEUM ("Museum"),
    PADEL ("Padel playground"),
    VEGAN_RESTURANT("Vegan Resturant"),
    FUN_FACT("fun fact")

}
