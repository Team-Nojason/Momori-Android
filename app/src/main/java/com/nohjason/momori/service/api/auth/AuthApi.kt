package com.nohjason.momori.service.api.auth

import com.nohjason.momori.service.model.request.JoinRequest
import com.nohjason.momori.service.model.request.LoginRequest
import com.nohjason.momori.service.model.request.TokenRequest
import com.nohjason.momori.service.model.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): TokenResponse

    @POST("/auth/join")
    suspend fun join(
        @Body request: JoinRequest
    ): TokenResponse

    @POST("/auth/check")
    suspend fun check()

    @POST("/auth/refresh")
    fun refresh(
        @Body request: TokenRequest
    ): TokenResponse
}
