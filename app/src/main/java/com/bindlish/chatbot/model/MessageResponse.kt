package com.bindlish.chatbot.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Aman Bindlish on 17,September,2019
 */
data class MessageResponse(
    @SerializedName("errorMessage")
    val error: String?,
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: Message
)