package com.project.dashboard.dao

import com.project.dashboard.model.AppId
import com.project.dashboard.model.DayPart
import com.project.dashboard.model.Download
import com.project.dashboard.model.Position
import java.time.Instant

interface DownloadDao {

    fun getAll() : List<Download>

    fun getByCountry(countryName: String) : List<Download>

    fun getByDayPart(dayPart: DayPart) : List<Download>

    fun getById(id: Long) : Download?

    fun save(downloadedAt: Instant, position: Position, appId: AppId, countryName: String, dayPart: DayPart) : Long
}