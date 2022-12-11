package com.handson.domain.repository

import com.handson.domain.data.home.Message
import com.handson.domain.data.home.MessageList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getMessageList() : Flow<List<Message>>
    fun deleteMessage(messageId: Long) : Flow<Boolean>
    fun changeVisibility(messageId: Long) : Flow<Boolean>
    fun sendMessage(title: String, description: String, receiverEmail: String, messageType: String, nameBlind: Boolean) : Flow<Boolean>
}