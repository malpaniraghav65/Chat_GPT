package com.example.chatgpt.models.request

data class Chatrequest(val maxTokens: Int = 0,
                       val temperature: Double = 0.0,
                       val model: String = "",
                       val prompt: String = "")