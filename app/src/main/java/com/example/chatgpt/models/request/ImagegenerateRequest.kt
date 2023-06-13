package com.example.chatgpt.models.request

data class ImagegenerateRequest(val size: String = "",
                                val prompt: String = "",
                                val n: Int = 0)