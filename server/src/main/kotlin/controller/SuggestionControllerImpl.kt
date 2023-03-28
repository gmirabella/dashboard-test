package com.project.dashboard.controller

import com.project.dashboard.model.*
import com.project.dashboard.service.SuggestionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
class SuggestionControllerImpl : SuggestionController{

    @Inject private lateinit var suggestionService: SuggestionService

    override fun getAll(countryName: String?, interestType: InterestType?) : ResponseEntity<SuggestionList> {
        //return ResponseEntity(suggestionService.getAll(countryName, interestType), HttpStatus.OK)
        TODO("Not yet implemented")
    }

}