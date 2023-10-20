package com.nohjason.momori.service.api.user

import com.nohjason.momori.service.model.request.LoginRequest
import com.nohjason.momori.service.model.response.TokenInfoResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("/users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): TokenInfoResponse
}
