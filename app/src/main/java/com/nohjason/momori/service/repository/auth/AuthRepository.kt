package com.nohjason.momori.service.repository.auth

import com.nohjason.momori.service.RetrofitClient
import com.nohjason.momori.service.model.request.JoinRequest
import com.nohjason.momori.service.model.request.LoginRequest
import com.nohjason.momori.service.model.request.TokenRequest

object AuthRepository {
    suspend fun login(loginRequest: LoginRequest) =
        RetrofitClient.authAPI.login(loginRequest)

    suspend fun join(joinRequest: JoinRequest) =
        RetrofitClient.authAPI.join(joinRequest)

    suspend fun refresh(tokenRequest: TokenRequest) =
        RetrofitClient.authAPI.refresh(tokenRequest)

    suspend fun check() =
        RetrofitClient.authAPI.check()

}