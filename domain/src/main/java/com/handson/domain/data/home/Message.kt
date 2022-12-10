package com.handson.domain.data.home

data class MessageResponse(
    val status : Int,
    val success : Boolean,
    val message: String,
    val data: MessageList
)

data class MessageList(
    val messages: List<Message>
)

data class MessageItemResponse(
    val status : Int,
    val success : Boolean,
    val message: String,
    val data: Message
)

data class Message(
    val messageId: Long,
    var hidden: Boolean,
    val description: String,
    val messageType: String,
    val senderNickName: String,
    val nameBlind: Boolean,
    val title: String
)
