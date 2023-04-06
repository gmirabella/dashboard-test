package com.project.dashboard.service

import com.project.dashboard.model.InterestType
import com.project.dashboard.model.Position
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.inject.Inject


@Service
class SuggestionServiceImpl : SuggestionService {

    @Inject private lateinit var openAIClient: OpenAIClient
    @Value("\${openai.token}") private lateinit var token: String
    override fun getSuggestions(position: Position, interestType: InterestType?): String {

        val input = if (interestType != null)"raccontami qualche ${interestType.name}" else "cosa c'è di interessante"
        val prompt =" $input vicino a queste coordinate: ${position.lat}, ${position.lon}"


        return openAIClient.askOpenAI(prompt, token)
    }

}
