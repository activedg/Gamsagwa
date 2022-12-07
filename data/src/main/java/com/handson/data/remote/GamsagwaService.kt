package com.handson.data.remote

import com.handson.data.model.profile.NicknameDto
import com.handson.domain.data.home.MessageList
import com.handson.domain.data.profile.NicknameResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH

interface GamsagwaService {
    // 홈화면 메시지 받기
    @GET("/message")
    suspend fun getMessageList() : MessageList

    // 닉네임 조회
    @GET("/user/nickname")
    suspend fun getNickname() : NicknameResponse
    // 닉네임 변경
    @PATCH("/user/nickname")
    suspend fun changeNickname(nicknameDto: NicknameDto) : NicknameResponse
    // 회원 탈퇴
    @DELETE("/auth/withdrawal")
    suspend fun removeUser() : Response<String?>
}