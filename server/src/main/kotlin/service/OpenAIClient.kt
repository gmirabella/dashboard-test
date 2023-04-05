package com.project.dashboard.service

import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

@Service
class OpenAIClient {


    fun askOpenAI(prompt: String, apiKey: String): String {
        val url = "https://api.openai.com/v1/chat/completions"
        val inputData = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": $prompt}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }"


        val postData = inputData.toByteArray(StandardCharsets.UTF_8)

        val conn = URL(url).openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Authorization", "Bearer $apiKey")
        conn.doOutput = true
        conn.outputStream.write(postData)

        val input = BufferedReader(InputStreamReader(conn.inputStream))
        val response = StringBuffer()

        var inputLine: String?
        while (input.readLine().also { inputLine = it } != null) {
            response.append(inputLine)
        }
        input.close()

        return response.toString()
    }

}