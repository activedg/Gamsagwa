package com.handson.thankapolo.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.handson.thankapolo.R

class MyFirebaseService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // 토큰 갱신 시 호출 -> 서버로 업데이트 된 토큰 보내기
        Log.d(TAG, "FCM TOKEN CREATED : $token")
        // sendTokenToServer(token)
    }

    fun getToken() : String? {
        var token: String? = null
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener{ task->
            if (!task.isSuccessful){
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            token = task.result
            Log.d(TAG, "FCM Token : $token")
        })
        return token
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val from = message.from
        val title = message.notification?.title
        val body = message.notification?.body

/*
        // 데이터 페이로드가 포함된 경우
        val requestId = Integer.parseInt(message.data["requestId"])
     */
        Log.d(TAG, "message received : $message")
        Log.d(TAG, "from : $from title : $title body : $body")

        title?.let { body?.let { sendNotification(title, body) } }
    }

    private fun sendNotification(title: String, text: String){

        val channelId = getString(R.string.default_notification_channel_id)
        val channelName = getString(R.string.default_notification_channel_name)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText(text)
            .setContentTitle(title)
//            .setContentIntent(resultPendingIntent)

        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        with(NotificationManagerCompat.from(this)){
            createNotificationChannel(channel)
            notify(0, notificationBuilder.build())
        }

    }

}