package com.project.dashboard.controller

import com.project.dashboard.model.*
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface SuggestionController{

    @PostMapping("/suggestions", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getSuggestions(@RequestBody(required = true) suggestionInput: SuggestionInput) : ResponseEntity<String>


}