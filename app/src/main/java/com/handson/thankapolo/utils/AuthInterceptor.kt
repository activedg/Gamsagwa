package com.handson.thankapolo.utils

import android.util.Log
import com.handson.thankapolo.ThankApoloApplication.Companion.spfManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = "Bearer ${spfManager.getUserToken()}"
        val authRequest = if (token.isNullOrEmpty()){
            originalRequest.newBuilder()
                .build()
        } else {
            originalRequest.newBuilder()
                .addHeader("Authorization", token)
                .build().also {
                    // Bearer Token
                    Log.e("okhttpHeader", token)
                }
        }
        return chain.proceed(authRequest)
    }
}