package com.bindlish.chatbot.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Aman Bindlish on 19,September,2019
 */
fun checkInternetConnection(context: Context): Boolean {
    val connManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}