package com.handson.data.model.login

data class UserSignInDto(
    val email: String,
    val password: String,
    val fcmToken: String
)
