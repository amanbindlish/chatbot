package com.bindlish.chatbot.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Aman Bindlish on 17,September,2019
 */
data class Message(
    val isMine: Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("chatBotID")
    val chatBotID: Long = 0,
    @SerializedName("chatBotName")
    val chatBotName: String? = "",
    @SerializedName("emotion")
    val emotion: String? = ""
)