package com.handson.domain.data.home

data class Message(
    val hidden: Boolean,
    val messageType: String,
    val nameBlind: Boolean,
    val title: String
)

data class MessageList(
    val messages: List<Message>
)