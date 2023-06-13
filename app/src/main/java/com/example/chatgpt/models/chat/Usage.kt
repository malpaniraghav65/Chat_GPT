package com.example.chatgpt.models.chat

data class Usage(val completionTokens: Int = 0,
                 val promptTokens: Int = 0,
                 val totalTokens: Int = 0)