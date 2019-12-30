package com.project.dashboard.controller

import com.project.dashboard.model.Download
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/downloads")
interface DownloadController{

    @GetMapping("/all")
    fun getAll() : ResponseEntity<List<Download>>

    @GetMapping("/{id}")
    fun getById(@PathVariable id : Long) : ResponseEntity<Download>

    @PostMapping("/new", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(download: Download) : ResponseEntity<Long>

}