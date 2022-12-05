package com.handson.thankapolo.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context) {
    private val spfManager: SharedPreferences =
        context.getSharedPreferences("gamsagwa", Context.MODE_PRIVATE)

    // userToken 메서드
    fun getUserToken(): String {
        return spfManager.getString("user_token", "").toString()
    }
    fun setUserToken(token: String) {
        spfManager.edit().putString("user_token", token).apply()
    }
}