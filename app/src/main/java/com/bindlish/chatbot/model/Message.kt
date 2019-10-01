package com.bindlish.chatbot.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Aman Bindlish on 17,September,2019
 */
@Entity
data class Message(
    @ColumnInfo(name = "isMine")
    var isMine: Boolean = false,

    @ColumnInfo(name = "message")
    @SerializedName("message")
    var message: String?,

    @ColumnInfo(name = "chatBotID")
    @SerializedName("chatBotID")
    var chatBotID: Long = 0,

    @ColumnInfo(name = "chatBotName")
    @SerializedName("chatBotName")
    var chatBotName: String? = "",

    @ColumnInfo(name = "emotion")
    @SerializedName("emotion")
    var emotion: String? = "",

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var mId: Int = 0,

    @ColumnInfo(name = "userName")
    @NonNull
    var userName: String = "",

    @ColumnInfo(name = "isSynced")
    var isSynced: Boolean = false
)