package com.handson.domain.data.profile


data class NicknameResponse(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Nickname
)
