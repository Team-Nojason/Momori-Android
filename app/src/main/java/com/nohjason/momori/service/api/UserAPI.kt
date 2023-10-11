package com.nohjason.momori.service.api

import com.nohjason.momori.service.model.UserResponse
import retrofit2.http.GET

interface UserAPI {
    @GET("/users/{userId}")
    fun getUser(userId: Int): UserResponse
}