package com.handson.data.remote

import com.handson.data.model.DefaultResponse
import com.handson.data.model.profile.NicknameDto
import com.handson.domain.data.home.*
import com.handson.domain.data.profile.NicknameResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GamsagwaService {
    // 홈화면 메시지 받기
    @GET("/message")
    suspend fun getMessageList() : MessageResponse

    // 닉네임 조회
    @GET("/user/nickname")
    suspend fun getNickname() : NicknameResponse
    // 닉네임 변경
    @PATCH("/user/nickname")
    suspend fun changeNickname(@Body nicknameDto: NicknameDto) : NicknameResponse
    // 회원 탈퇴
    @DELETE("/auth/withdrawal")
    suspend fun removeUser() : Response<String?>

    // 메시지 삭제
    @DELETE("/message/{messageId}")
    suspend fun deleteMessage(@Path("messageId") messageId: Long) : DefaultResponse
    // 메시지 숨김 관리
    @PATCH("/message/hidden/{messageId}")
    suspend fun changeMessageVisible(@Path("messageId") messageId: Long) : MessageItemResponse
    // 메시지 전송
    @POST("/message")
    suspend fun sendMessage(@Body messageSend: MessageSend) : MessageSendResponse
}