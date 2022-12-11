package com.handson.domain.data.home

data class MessageSend(
    val title: String,
    val description: String,
    val receiverEmail: String,
    val messageType: String,
    val nameBlind: Boolean
)

data class MessageSendResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: MessageSend
)