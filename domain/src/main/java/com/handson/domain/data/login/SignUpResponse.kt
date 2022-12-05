package com.handson.domain.data.login

data class SignUpResponse(
    val data: SingUpData,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class SingUpData(
    val email: String,
    val nickname: String
)