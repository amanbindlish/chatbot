package com.bindlish.chatbot.di

import com.bindlish.chatbot.view.ChatFragment
import com.bindlish.chatbot.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Aman Bindlish on 18,September,2019
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeChatFragment(): ChatFragment

}