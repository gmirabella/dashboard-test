package com.project.dashboard.service

import com.project.dashboard.model.Download
import com.project.dashboard.model.InputDownload

interface DownloadService{

    fun getAll() : List<Download>

    fun getById(id: Long) : Download?

    fun save(input: InputDownload) : Long
}