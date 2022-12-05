package com.handson.thankapolo.notification

import android.content.ContentValues.TAG
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

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
}