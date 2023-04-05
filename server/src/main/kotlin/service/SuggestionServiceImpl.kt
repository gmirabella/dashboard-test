package com.project.dashboard.service

import com.project.dashboard.model.InterestType
import com.project.dashboard.model.Position
import org.springframework.stereotype.Service
import javax.inject.Inject


@Service
class SuggestionServiceImpl : SuggestionService {

    @Inject private lateinit var openAIClient: OpenAIClient
    override fun getSuggestions(position: Position, interestType: InterestType?): String {

        val prompt = "cosa c'è di interessante a queste coordinate: ${position.lat}, ${position.lon}"
        return openAIClient.askOpenAI(prompt, "APIKEY")
    }

}
