package com.project.dashboard.controller

import com.project.dashboard.model.Filters
import com.project.dashboard.service.FilterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import javax.inject.Inject

@Controller
class FilterControllerImpl : FilterController{

    @Inject private lateinit var filterService: FilterService

    override fun getFilters(): ResponseEntity<Filters> {
        return ResponseEntity(filterService.getFilters(), HttpStatus.OK)
    }
}