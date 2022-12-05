package com.handson.data.remote

import com.handson.data.model.login.UserSignInDto
import com.handson.data.model.login.UserSignUpDto
import com.handson.domain.data.login.SignInResponse
import com.handson.domain.data.login.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GamsagwaLoginService {
    @POST("/auth/signUp")
    suspend fun signUp(@Body userSignUpDto: UserSignUpDto) : SignUpResponse

    @POST("auth/signIn")
    suspend fun signIn(@Body userSignInDto: UserSignInDto) : SignInResponse
}