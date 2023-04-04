package com.project.dashboard.service

import com.project.dashboard.model.InterestType
import com.project.dashboard.model.Position

interface SuggestionService {

    fun getSuggestions(position: Position, interestType: InterestType?) : String

}