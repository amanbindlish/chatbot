package com.bindlish.chatbot.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bindlish.chatbot.model.Message
import javax.inject.Singleton

/**
 * Created by Aman Bindlish on 18,September,2019
 */
@Singleton
@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
}