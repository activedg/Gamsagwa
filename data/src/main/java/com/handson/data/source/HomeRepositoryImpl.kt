package com.handson.data.source

import android.util.Log
import com.handson.data.remote.GamsagwaService
import com.handson.domain.data.home.Message
import com.handson.domain.data.home.MessageList
import com.handson.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val gamsagwaService: GamsagwaService
) : HomeRepository{
    override fun getMessageList()
    : Flow<List<Message>> = flow {
        emit(gamsagwaService.getMessageList().data.messages)
    }.flowOn(Dispatchers.IO)

    override fun deleteMessage(
        messageId: Long
    ): Flow<Boolean> = flow {
        emit(gamsagwaService.deleteMessage(messageId).success)
    }.flowOn(Dispatchers.IO)
}