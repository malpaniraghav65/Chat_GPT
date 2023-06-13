package com.example.chatgpt.models.chat

data class ChatModel(val created: Int = 0,
                     val usage: Usage,
                     val model: String = "",
                     val id: String = "",
                     val choices: List<ChoicesItem>?,
                     val objects: String = "")