package com.example.chatgpt.models.chat

data class ChoicesItem(val finishReason: String = "",
                       val index: Int = 0,
                       val text: String = "",
                       val logprobs:String?= null)