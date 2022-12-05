package com.handson.domain.data.login

data class SignInResponse(
    val data: SignInData,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class SignInData(
    val accessToken: String,
    val email: String,
    val nickname: String
)