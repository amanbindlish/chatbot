package com.bindlish.chatbot.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Aman Bindlish on 17,September,2019
 */
interface DataApi {

    @GET("api/chat/")
    fun getBotChat(
        @Query("message") message: String,
        @Query("externalID") userName: String?,
        @Query("apiKey") apiKey: String = "6nt5d1nJHkqbkphe",
        @Query("chatBotID") chatBotId: String = "63906"
    ): Single<MessageResponse>
}