package com.project.dashboard.service

import com.project.dashboard.dao.DownloadDao
import com.project.dashboard.model.Download
import com.project.dashboard.model.InputDownload
import org.springframework.stereotype.Service
import java.time.Instant
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

    override fun save(input: InputDownload): Long {
        return downloadDao.save(Instant.now(), input.position, input.appId)
    }
}