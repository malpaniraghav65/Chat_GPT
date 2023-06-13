package com.example.chatgpt

data class MessageModel(
    var isUser : Boolean,
    var isImage : Boolean,
    var message: String
)
