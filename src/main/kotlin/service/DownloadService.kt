package com.project.dashboard.service

import com.project.dashboard.model.Download

interface DownloadService{

    fun getAll() : List<Download>

    fun getById(id: Long) : Download?

    fun create(download: Download) : Long
}