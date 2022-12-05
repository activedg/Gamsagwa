package com.handson.domain.data.login.repository

import com.handson.domain.data.login.SignInResponse
import com.handson.domain.data.login.SignUpResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun signUp(email: String, password: String, nickname: String) : Flow<SignUpResponse>
    fun signIn(email: String, password: String, fcmToken: String) : Flow<SignInResponse>
}