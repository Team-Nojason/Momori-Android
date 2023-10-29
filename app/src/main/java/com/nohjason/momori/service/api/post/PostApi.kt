package com.nohjason.momori.service.api.post

import com.nohjason.momori.service.model.request.AddPostRequest
import com.nohjason.momori.service.model.request.EditPostRequest
import com.nohjason.momori.service.model.response.PostResponse
import com.nohjason.momori.service.model.response.etc.MessageResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {

    @GET("/post/{post_id}")
    suspend fun getPostById(
        @Path("post_id") postId: Int
    ): PostResponse

    @GET("/post")
    suspend fun getPostByLocation(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float
    ): List<PostResponse>

    @GET("/post/user/{user_id}")
    suspend fun getPostByUser(
        @Path("user_id") userId: Int?
    ): List<PostResponse>

    @POST("/post/{post_id}")
    suspend fun addPost(
        @Body request: AddPostRequest,
        @Path("post_id") postId: Int
    ): PostResponse

    @PUT("/post/{post_id}")
    suspend fun editPost(
        @Body request: EditPostRequest,
        @Path("post_id") postId: Int
    ): PostResponse

    @DELETE("/post/{post_id}")
    suspend fun removePost(
        @Path("post_id") postId: Int
    ): MessageResponse
}