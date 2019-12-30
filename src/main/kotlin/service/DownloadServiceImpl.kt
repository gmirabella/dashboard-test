package com.project.dashboard.service

import com.project.dashboard.dao.DownloadDao
import com.project.dashboard.model.Download
import org.springframework.stereotype.Service
import javax.inject.Inject

@Service
class DownloadServiceImpl : DownloadService {

    @Inject private lateinit var downloadDao: DownloadDao

    override fun getAll(): List<Download> {
        return downloadDao.getAll()
    }

    override fun getById(id: Long): Download? {
        return downloadDao.getById(id)
    }

    override fun create(download: Download): Long {
        return downloadDao.save(download.downloadedAt, download.pos, download.appId)
    }
}