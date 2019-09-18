package com.bindlish.chatbot.di

import android.app.Application
import com.bindlish.chatbot.ChatApplication
import com.bindlish.chatbot.model.DataService
import com.bindlish.chatbot.viewmodel.ChatViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Aman Bindlish on 17,September,2019
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, ApiModule::class, BuildersModule::class])
interface ApiComponent {

    fun inject(application: ChatApplication)
}