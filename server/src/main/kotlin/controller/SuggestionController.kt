package com.project.dashboard.controller

import com.project.dashboard.model.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface SuggestionController{

    @GetMapping("/suggestions")
    fun getSuggestions(@RequestParam(required = true) position: Position, @RequestParam(required = false) interestType: InterestType?) : ResponseEntity<String>


}