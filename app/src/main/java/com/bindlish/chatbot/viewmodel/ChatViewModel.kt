package com.bindlish.chatbot.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bindlish.chatbot.di.DaggerApiComponent
import com.bindlish.chatbot.model.DataService
import com.bindlish.chatbot.model.Message
import com.bindlish.chatbot.model.MessageResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class ChatViewModel : ViewModel() {

    @Inject
    lateinit var dataService: DataService

    private val disposable = CompositeDisposable()
    val message = MutableLiveData<Message>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun sendMessage(msg: String?, user: String?) {
        if (msg!!.isEmpty() || user!!.isEmpty()) {
            return
        }
        loading.value = true
        disposable.add(
            dataService.getBotChat(msg, user).subscribeOn(Schedulers.newThread())
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
}