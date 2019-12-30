package com.project.dashboard.controller

import com.project.dashboard.DownloadNotFoundException
import com.project.dashboard.model.Download
import com.project.dashboard.service.DownloadService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
class DownloadControllerImpl : DownloadController{

    @Inject private lateinit var downloadService: DownloadService

    override fun getAll() : ResponseEntity<List<Download>> {
        return ResponseEntity(downloadService.getAll(), HttpStatus.OK)
    }

    override fun getById(id: Long): ResponseEntity<Download> {
       val download = downloadService.getById(id) ?: throw DownloadNotFoundException("Download with id <$id> not found")
        return ResponseEntity(download, HttpStatus.OK)
    }

    override fun save(download: Download) : ResponseEntity<Long>  {
        return ResponseEntity(downloadService.create(download), HttpStatus.OK)
    }
}