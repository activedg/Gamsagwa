package com.handson.data.source

import com.handson.data.model.login.UserSignInDto
import com.handson.data.model.login.UserSignUpDto
import com.handson.data.remote.GamsagwaLoginService
import com.handson.domain.data.login.SignInResponse
import com.handson.domain.data.login.SignUpResponse
import com.handson.domain.data.login.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val gamsagwaLoginService: GamsagwaLoginService
) : LoginRepository {
    override fun signUp(
        email: String, password: String, nickname: String
    ): Flow<SignUpResponse> = flow {
        emit(gamsagwaLoginService.signUp(UserSignUpDto(email, password, nickname)))
    }.flowOn(Dispatchers.IO)

    override fun signIn(
        email: String, password: String, fcmToken: String
    ): Flow<SignInResponse> = flow {
        emit(gamsagwaLoginService.signIn(UserSignInDto(email, password, fcmToken)))
    }.flowOn(Dispatchers.IO)
}