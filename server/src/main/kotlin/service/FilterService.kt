package com.project.dashboard.service

import com.project.dashboard.model.Country
import com.project.dashboard.model.DayPart
import com.project.dashboard.model.Filters
import com.project.dashboard.model.Geocode
import java.time.LocalTime


interface FilterService{

    fun getFilters(): Filters

    fun calculateCountryFromLatLog(lat: String, lon: String) : Country

}