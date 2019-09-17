package com.bindlish.chatbot.di

import com.bindlish.chatbot.model.DataApi
import com.bindlish.chatbot.model.DataService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Aman Bindlish on 17,September,2019
 */
@Module
class ApiModule {

    private val BASE_URL = "http://192.168.1.6:5000/"

    @Provides
    fun provideDataApi(): DataApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(DataApi::class.java)

    @Provides
    fun provideDataService(): DataService = DataService()

    //    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)
        return httpClient.build()
    }

}