package com.project.dashboard.controller

import com.project.dashboard.model.Download
import com.project.dashboard.model.DownloadList
import com.project.dashboard.model.InputDownload
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface DownloadController{

    @GetMapping("/downloads")
    fun getAll(@RequestParam(required = false) countryName: String?) : ResponseEntity<DownloadList>

    @GetMapping("/downloads/{id}")
    fun getById(@PathVariable id : Long) : ResponseEntity<Download>

    @PostMapping("/downloads", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody input: InputDownload) : ResponseEntity<Long>

}