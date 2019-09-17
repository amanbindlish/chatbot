package com.bindlish.chatbot.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bindlish.chatbot.R
import com.bindlish.chatbot.model.Message
import kotlinx.android.synthetic.main.received_message_layout.view.*
import kotlinx.android.synthetic.main.sent_message_layout.view.*
import kotlinx.android.synthetic.main.sent_message_layout.view.msg_text

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class ChatAdapter(private var messageList: ArrayList<Message>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private val SENT_MESSAGE = 1
    private val RECEIVE_MESSAGE = 2

    fun updateMessages(msg: Message) {
        messageList.add(msg)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        if (viewType == SENT_MESSAGE) {
            return ChatViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.sent_message_layout,
                    parent,
                    false
                )
            )
        } else {
            return ChatViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.received_message_layout,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int = messageList.size

    override fun getItemViewType(position: Int): Int {
        val msg = messageList.get(position)
        if (msg.isMine) {
            return SENT_MESSAGE
        } else {
            return RECEIVE_MESSAGE
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messageList[position])
    }

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val message = view.msg_text
        private val botName = view.bot_name

        fun bind(msg: Message) {
            message.text = msg.message
            botName?.text = msg.chatBotName
        }

    }
}