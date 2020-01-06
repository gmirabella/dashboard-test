package com.project.dashboard.service

import com.project.dashboard.model.DayPart
import com.project.dashboard.model.Download
import com.project.dashboard.model.DownloadList
import com.project.dashboard.model.InputDownload
import java.time.Instant

interface DownloadService{

    fun getAll(countryName: String?, dayPart: DayPart?) : DownloadList

    fun getById(id: Long) : Download?

    fun save(now: Instant, input: InputDownload) : Long
}