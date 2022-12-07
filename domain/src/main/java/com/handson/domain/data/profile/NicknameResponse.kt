package com.handson.domain.data.profile

import com.handson.domain.data.login.SignInData

data class NicknameResponse(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Nickname
)
