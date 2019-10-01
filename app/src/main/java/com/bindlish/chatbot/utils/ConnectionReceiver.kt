package com.bindlish.chatbot.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

/**
 * Created by Aman Bindlish on 19,September,2019
 */
class ConnectionReceiver : BroadcastReceiver() {

    interface ConnectionReceiverListener {
        fun onNetworkChanged(isConnected: Boolean)
    }

    companion object {
        var connectionReceiverListener: ConnectionReceiverListener? = null
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (connectionReceiverListener != null) {
            connectionReceiverListener!!.onNetworkChanged(checkInternetConnection(context))
        }
    }


}