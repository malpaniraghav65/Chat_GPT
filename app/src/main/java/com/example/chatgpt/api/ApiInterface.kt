package com.example.chatgpt.api

import android.app.DownloadManager.Request
import com.example.chatgpt.models.chat.ChatModel
import com.example.chatgpt.models.imageresponse.GenrateImageModel
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/v1/images/generations")
    suspend fun generateImage(@Header ("Content-type") contentType: String,
                              @Header ("Authorization") authorization: String,
                              @Body requestBody: RequestBody): GenrateImageModel
    @POST("/v1/completions")
    suspend fun getChat(
        @Header ("Content-type") contentType: String,
        @Header ("Authorization") authorization: String,
        @Body requestBody: RequestBody
    ): ChatModel
}
