package com.bindlish.chatbot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Created by Aman Bindlish on 18,September,2019
 */
class ChatViewModelFactory @Inject constructor(private val viewModel: ChatViewModel) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}