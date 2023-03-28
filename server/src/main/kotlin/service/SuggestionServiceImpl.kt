package com.project.dashboard.service

import com.project.dashboard.model.InterestType
import com.project.dashboard.model.SuggestionList
import org.springframework.stereotype.Service

@Service
class SuggestionServiceImpl : SuggestionService {

    override fun getAll(countryName: String?, interestType: InterestType?): SuggestionList {
        TODO("Not yet implemented")
    }
}