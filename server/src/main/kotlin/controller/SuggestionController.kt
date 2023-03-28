package com.project.dashboard.controller

import com.project.dashboard.model.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface SuggestionController{

    @GetMapping("/suggestions")
    fun getAll(@RequestParam(required = false) countryName: String?, @RequestParam(required = false) interestType: InterestType?) : ResponseEntity<SuggestionList>


}