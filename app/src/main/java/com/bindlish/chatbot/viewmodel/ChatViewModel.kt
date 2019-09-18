package com.bindlish.chatbot.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bindlish.chatbot.di.DaggerApiComponent
import com.bindlish.chatbot.model.Message
import com.bindlish.chatbot.model.MessageResponse
import com.bindlish.chatbot.model.repository.ChatRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository) : ViewModel() {

    private val disposable = CompositeDisposable()
    val message = MutableLiveData<Message>()
    val messageList = MutableLiveData<List<Message>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    // method to find local stored messages
    fun findMessages(user: String) {
        loading.value = true
        disposable.add(
            chatRepository.fetchAllMessages(user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Message>>() {
                    override fun onSuccess(messages: List<Message>?) {
                        messageList.postValue(messages)
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("chat view model", e?.message!!)
                    }

                })
        )
    }

    // method to send message and receive from api
    fun sendMessage(msg: String?, user: String?) {
        if (msg!!.isEmpty() || user!!.isEmpty()) {
            return
        }
        loading.value = true
        disposable.add(
            chatRepository.sendMessage(msg, user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MessageResponse>() {
                    override fun onSuccess(value: MessageResponse?) {
                        handleMessageResponse(value)
                        loading.value = false
                        loadError.value = false
                    }

                    override fun onError(e: Throwable?) {
                        loading.value = false
                        loadError.value = true
                    }
                })
        )

    }

    private fun handleMessageResponse(value: MessageResponse?) {
        if (value?.success == 1) {
            message.value = value.message
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}