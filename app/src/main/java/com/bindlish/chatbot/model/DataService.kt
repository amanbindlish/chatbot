package com.bindlish.chatbot.model

import androidx.annotation.NonNull
import com.bindlish.chatbot.ChatApplication
import com.bindlish.chatbot.di.DaggerApiComponent
import javax.inject.Inject

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class DataService @Inject constructor(private val dataApi: DataApi) {

    fun getBotChat(@NonNull message: String, @NonNull userName: String) =
        dataApi.getBotChat(message, userName)
}