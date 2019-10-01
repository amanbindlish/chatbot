package com.bindlish.chatbot.model.repository

import androidx.lifecycle.MutableLiveData
import com.bindlish.chatbot.ChatApplication
import com.bindlish.chatbot.model.DataService
import com.bindlish.chatbot.model.Message
import com.bindlish.chatbot.model.MessageResponse
import com.bindlish.chatbot.model.db.ChatDao
import com.bindlish.chatbot.utils.checkInternetConnection
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Aman Bindlish on 18,September,2019
 */
@Singleton
class ChatRepository @Inject constructor(
    private val dataService: DataService,
    private val chatDao: ChatDao
) {

    // responsible for sending message and storing locally
    fun sendMessage(msg: String, user: String): Single<MessageResponse> {
        val message = Message(true, msg, userName = user)
        insertMessage(message)
        return dataService.getBotChat(msg, user).map {
            it.apply {
                if (this.success == 1) {
                    val mess = this.message
                    mess.userName = user
                    insertMessage(mess)
                }
            }
        }
    }

    // this will fetch all messages of user from local db
    fun fetchAllMessages(user: String): Single<List<Message>> {
        return chatDao.getChatsByUser(user)
    }

    // method to insert message in local db using observable
    fun insertMessage(msg: Message) {
        Observable.just(chatDao).subscribeOn(Schedulers.io()).subscribe {
            it.insertChat(msg)
        }
    }
}