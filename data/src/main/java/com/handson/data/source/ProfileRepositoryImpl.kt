package com.handson.data.source

import com.handson.data.model.profile.NicknameDto
import com.handson.data.remote.GamsagwaService
import com.handson.domain.data.profile.Nickname
import com.handson.domain.data.profile.NicknameResponse
import com.handson.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val gamsagwaService: GamsagwaService
) : ProfileRepository{
    override fun getNickname(): Flow<NicknameResponse> = flow {
        emit(gamsagwaService.getNickname())
    }.flowOn(Dispatchers.IO)

    override fun changeNickname(
        nickName: String
    ): Flow<NicknameResponse> = flow {
        emit(gamsagwaService.changeNickname(NicknameDto(nickName)))
    }.flowOn(Dispatchers.IO)

    override fun removeUser()
    : Flow<Boolean> = flow {
        emit(gamsagwaService.removeUser().isSuccessful)
    }.flowOn(Dispatchers.IO)
}