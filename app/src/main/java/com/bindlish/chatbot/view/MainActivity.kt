package com.bindlish.chatbot.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bindlish.chatbot.R
import dagger.android.AndroidInjection

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // for dependency injection
        AndroidInjection.inject(this)
        // check if fragment available in fragmentManager
        var chatFragment =
            supportFragmentManager.findFragmentByTag("chat_fragment")
        // if fragment is null then create instance and add
        if (chatFragment == null) {
            chatFragment = ChatFragment.getInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_container, chatFragment, "chat_fragment").commit()
        }
    }
}
