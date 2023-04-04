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
        val url = "https://api.openai.com/v1/engines/davinci-codex/completions"
        val data = "{\"prompt\": \"$prompt\",\"max_tokens\": 150,\"temperature\": 0.7,\"n\": 1,\"stop\": \".\"}"
        val postData = data.toByteArray(StandardCharsets.UTF_8)

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