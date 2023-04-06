package com.project.dashboard.controller

import com.project.dashboard.dao.DownloadDaoImpl
import com.project.dashboard.model.*
import com.project.dashboard.service.SuggestionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
class SuggestionControllerImpl : SuggestionController{

    @Inject private lateinit var suggestionService: SuggestionService

    private val log : Logger = LoggerFactory.getLogger(this::class.java)
    override fun getSuggestions(suggestionInput: SuggestionInput): ResponseEntity<String> {
        log.info("position: ${suggestionInput.position}")
        return ResponseEntity(suggestionService.getSuggestions(suggestionInput.position, suggestionInput.interestType), HttpStatus.OK)
    }


}