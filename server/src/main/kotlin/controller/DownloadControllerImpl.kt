package com.project.dashboard.controller

import com.project.dashboard.DownloadNotFoundException
import com.project.dashboard.model.*
import com.project.dashboard.service.DownloadService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import javax.inject.Inject

@RestController
class DownloadControllerImpl : DownloadController{

    @Inject private lateinit var downloadService: DownloadService

    override fun getAll(countryName: String?, dayPart: DayPart?) : ResponseEntity<DownloadList> {
        return ResponseEntity(downloadService.getAll(countryName, dayPart), HttpStatus.OK)
    }

    override fun getById(id: Long): ResponseEntity<Download> {
       val download = downloadService.getById(id) ?: throw DownloadNotFoundException("Download with id <$id> not found")
        return ResponseEntity(download, HttpStatus.OK)
    }

    override fun save(input: InputDownload) : ResponseEntity<Long>  {
        return ResponseEntity(downloadService.save(Instant.now(), input), HttpStatus.OK)
    }
}