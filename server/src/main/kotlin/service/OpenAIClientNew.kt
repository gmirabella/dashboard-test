package com.project.dashboard.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.*
import org.springframework.http.HttpEntity
import java.lang.IllegalArgumentException
import java.util.HashMap
import javax.inject.Inject

@Service
class OpenAIClientNew {

    private val log : Logger = LoggerFactory.getLogger(this::class.java)
    @Inject private lateinit var restTemplate: RestTemplate

    fun askOpenAI(prompt: String, apiKey: String): String {
        val url = "https://api.openai.com/v1/chat/completions"
        val inputData = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": $prompt}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }"


         try {
            val headers = HttpHeaders()

            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
            headers.contentType = MediaType.APPLICATION_JSON
            headers.add("Authorization", "Bearer $apiKey")

            val map = HashMap<String, String>()
            map["text"] = inputData

            val entity = HttpEntity<Map<String, Any>>(map, headers)
           val res = restTemplate.postForObject(url, entity, String::class.java)!!
             log.info("res: $res")
             return res
                 //.response.choices.first().message.content

        } catch (exc: Throwable) {
            throw IllegalArgumentException("Could not retrieve data")
        }
    }
    }