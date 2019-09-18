package com.bindlish.chatbot.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bindlish.chatbot.ChatApplication
import com.bindlish.chatbot.model.DataApi
import com.bindlish.chatbot.model.DataService
import com.bindlish.chatbot.model.db.ChatDao
import com.bindlish.chatbot.model.db.ChatDatabase
import com.bindlish.chatbot.model.repository.ChatRepository
import com.bindlish.chatbot.viewmodel.ChatViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Aman Bindlish on 17,September,2019
 */
@Module
class ApiModule(private val application: ChatApplication) {

    private val BASE_URL = "http://192.168.43.231:5000/"

    @Provides
    @Singleton
    fun provideContext(): ChatApplication = application

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideDataApi(retrofit: Retrofit): DataApi = retrofit.create(DataApi::class.java)

    @Provides
    fun provideDataService(dataApi: DataApi): DataService = DataService(dataApi)

    @Singleton
    @Provides
    fun provideChatDataBase(application: ChatApplication): ChatDatabase =
        Room.databaseBuilder(application, ChatDatabase::class.java, "chat-db").build()

    @Provides
    @Singleton
    fun provideChatDao(chatDatabase: ChatDatabase): ChatDao = chatDatabase.chatDao()

    @Provides
    @Singleton
    fun provideChatRepository(dataService: DataService, chatDao: ChatDao): ChatRepository =
        ChatRepository(dataService, chatDao)

    @Provides
    @Singleton
    fun provideViewModelFactory(chatViewModelFactory: ChatViewModelFactory): ViewModelProvider.Factory =
        chatViewModelFactory
}