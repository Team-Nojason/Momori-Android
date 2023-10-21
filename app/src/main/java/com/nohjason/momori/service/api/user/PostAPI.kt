package com.nohjason.momori.service.api.user

import com.nohjason.momori.service.model.request.PostRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface PostAPI {
    @POST("/post")
    suspend fun addPost(
        @Body request: PostRequest
    )
}