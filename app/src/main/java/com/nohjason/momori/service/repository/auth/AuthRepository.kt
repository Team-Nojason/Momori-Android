package com.nohjason.momori.service.repository.auth

import com.nohjason.momori.service.RetrofitClient
import com.nohjason.momori.service.model.request.JoinRequest
import com.nohjason.momori.service.model.request.LoginRequest
import com.nohjason.momori.service.model.request.TokenRequest

object AuthRepository {
    suspend fun login(loginRequest: LoginRequest) =
        RetrofitClient.authApi.login(loginRequest)

    suspend fun join(joinRequest: JoinRequest) =
        RetrofitClient.authApi.join(joinRequest)

    suspend fun refresh(tokenRequest: TokenRequest) =
        RetrofitClient.authApi.refresh(tokenRequest)

    suspend fun check() =
        RetrofitClient.authApi.check()

}