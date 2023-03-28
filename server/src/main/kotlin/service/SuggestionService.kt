package com.project.dashboard.service

import com.project.dashboard.model.InterestType
import com.project.dashboard.model.SuggestionList

interface SuggestionService {

    fun getAll(countryName: String?, interestType: InterestType?) : SuggestionList
}