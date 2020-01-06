package com.project.dashboard.service

import com.project.dashboard.model.DayPart
import com.project.dashboard.model.Geocode
import java.time.LocalTime

interface CalculateFilterService {

    fun calculateCountryFromLatLog(lat: String, lon: String) : Geocode

    fun calculateDayPart(hour: LocalTime) : DayPart
}