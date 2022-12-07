package com.handson.domain.repository

import com.handson.domain.data.profile.NicknameResponse
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getNickname() : Flow<NicknameResponse>
    fun changeNickname(nickName: String) : Flow<NicknameResponse>
    fun removeUser() : Flow<Boolean>
}