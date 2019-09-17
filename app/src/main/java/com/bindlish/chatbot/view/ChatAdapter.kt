package com.bindlish.chatbot.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bindlish.chatbot.R

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.sent_message_layout,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
    }

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}