package com.project.dashboard.model

data class OpenAIResponse(val response: Response)

data class Response(val choices: List<Choice>)

data class Choice(val message: Message)
data class Message( val content: String)
