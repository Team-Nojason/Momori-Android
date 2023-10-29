package com.nohjason.momori.service.api.comment

import com.nohjason.momori.service.model.request.AddCommentRequest
import com.nohjason.momori.service.model.response.CommentResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentApi {

    @GET("/comment/post/{post_id}")
    suspend fun getComment(
        @Path("post_id") postId: Int
    ): List<CommentResponse>

    @POST("/comment/post/{post_id}")
    suspend fun addComment(
        @Path("post_id") postId: Int,
        @Body request: AddCommentRequest
    ): CommentResponse

    @DELETE("/comment/{comment_id}")
    suspend fun removeComment(
        @Path("comment_id") commentId: Int
    )

}