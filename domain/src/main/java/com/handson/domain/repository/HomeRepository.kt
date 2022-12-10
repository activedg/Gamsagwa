package com.handson.domain.repository

import com.handson.domain.data.home.Message
import com.handson.domain.data.home.MessageList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getMessageList() : Flow<List<Message>>
    fun deleteMessage(messageId: Long) : Flow<Boolean>
}