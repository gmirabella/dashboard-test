package com.project.dashboard.controller

import com.project.dashboard.model.Filters
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping


interface FilterController{

    @GetMapping("/filters")
    fun getFilters(): ResponseEntity<Filters>
}