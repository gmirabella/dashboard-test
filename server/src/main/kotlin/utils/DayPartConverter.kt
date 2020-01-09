package com.project.dashboard.utils

import com.project.dashboard.model.DayPart
import java.time.LocalTime
import java.time.format.DateTimeFormatter



object DayPartConverter {

    var formatter = DateTimeFormatter.ISO_LOCAL_TIME

    fun parse(hour: LocalTime): DayPart {
        return if (hour.isAfter(LocalTime.parse("00:00:00", formatter)) && hour.isBefore(LocalTime.parse("06:00:00", formatter))) DayPart.NIGHT
        else if (hour.isAfter(LocalTime.parse("06:00:00", formatter)) && hour.isBefore(LocalTime.parse("12:00:00", formatter))) DayPart.MORNING
        else if (hour.isAfter(LocalTime.parse("12:00:00", formatter)) && hour.isBefore(LocalTime.parse("18:00:00", formatter))) DayPart.AFTERNOON
        else DayPart.EVENING

    }
}