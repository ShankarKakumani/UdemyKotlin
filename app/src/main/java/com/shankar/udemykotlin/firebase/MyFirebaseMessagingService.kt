package com.shankar.udemykotlin.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        const val TAG = "MyFirebaseMessaging"
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)


        Log.d(TAG, "FCM msg Id : ${remoteMessage.messageId}")
        Log.d(TAG, "FCM Notification Msg : ${remoteMessage.notification}")
        Log.d(TAG, "FCM Data message : ${remoteMessage.data}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }
}