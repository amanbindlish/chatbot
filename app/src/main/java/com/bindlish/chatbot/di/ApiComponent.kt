package com.bindlish.chatbot.di

import com.bindlish.chatbot.model.DataService
import com.bindlish.chatbot.viewmodel.ChatViewModel
import dagger.Component

/**
 * Created by Aman Bindlish on 17,September,2019
 */
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(dataService: DataService)

    fun inject(viewModel: ChatViewModel)
}