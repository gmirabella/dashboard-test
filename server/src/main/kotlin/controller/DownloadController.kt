package com.project.dashboard.controller

import com.project.dashboard.model.*
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface DownloadController{

    @GetMapping("/downloads")
    fun getAll(@RequestParam(required = false) countryName: String?, @RequestParam(required = false) dayPart: DayPart?, @RequestParam(required = false) period: PeriodDays?) : ResponseEntity<DownloadList>

    @PostMapping("/downloads", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody input: InputDownload) : ResponseEntity<Long>

}