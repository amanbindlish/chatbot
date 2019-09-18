package com.bindlish.chatbot

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.bindlish.chatbot.di.ApiModule
import com.bindlish.chatbot.di.DaggerApiComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Aman Bindlish on 18,September,2019
 */
class ChatApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate() {
        super.onCreate()

        DaggerApiComponent.builder()
            .apiModule(ApiModule(this))
            .build().inject(this)
    }
}