package com.bindlish.chatbot.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bindlish.chatbot.R
import com.bindlish.chatbot.model.Message
import com.bindlish.chatbot.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.fragment_chat.*

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class ChatFragment : Fragment() {

    companion object {
        fun getInstance(): ChatFragment = ChatFragment()
    }

    private lateinit var viewModel: ChatViewModel
    private val chatAdapter = ChatAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // to handle the configuration change
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialise viewModel from provider
        viewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // initialising recycler view
        chat_recycler?.apply {
            val llm = LinearLayoutManager(this.context)
            // for starting chat from bottom
            llm.stackFromEnd = true
            layoutManager = llm
            adapter = chatAdapter
        }
        // handle click of send button
        send_button.setOnClickListener {
            // hit api only if text size is greater than 0
            if (chat_text_box.text?.toString()?.trim()?.length?.compareTo(0) != 0) {
                empty_chat_layout.visibility = View.GONE
                chat_recycler.visibility = View.VISIBLE
                // add sent message as soon as click on send
                chatAdapter.updateMessages(Message(true, chat_text_box.text.toString()))
                chat_recycler.smoothScrollToPosition(chatAdapter.itemCount - 1)
                // hit api to send message and get bot message
                viewModel.sendMessage(chat_text_box.text?.toString()?.trim(), "aman")
                // clear chat box
                chat_text_box.text.clear()
            }
        }
        // observe
        observeChatData()
    }

    private fun observeChatData() {
        viewModel.message.observe(viewLifecycleOwner, Observer { message ->
            // update adpater on success response
            chatAdapter.updateMessages(message)
            chat_recycler.smoothScrollToPosition(chatAdapter.itemCount - 1)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                loader.visibility = View.VISIBLE
            } else {
                loader.visibility = View.GONE
            }
        })
        viewModel.loadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                if (isError) {
                    Toast.makeText(this.context, "Some error occurred", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}