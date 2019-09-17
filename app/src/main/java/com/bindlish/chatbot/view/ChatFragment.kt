package com.bindlish.chatbot.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bindlish.chatbot.R
import kotlinx.android.synthetic.main.fragment_chat.*

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class ChatFragment : Fragment() {

    companion object {
        fun getInstance(): ChatFragment = ChatFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        chat_recycler?.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = ChatAdapter()
        }
    }
}