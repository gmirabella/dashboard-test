package com.project.dashboard.service

import com.project.dashboard.model.*
import java.time.Instant
import java.time.LocalDateTime

interface DownloadService{

    fun getAll(countryName: String?, dayPart: DayPart?, period: PeriodDays?) : DownloadList

    fun save(now: Instant, input: InputDownload) : Long
}