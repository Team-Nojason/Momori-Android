package com.nohjason.momori.service.repository.comment

import com.nohjason.momori.service.RetrofitClient
import com.nohjason.momori.service.model.request.AddCommentRequest

object CommentRepository {
    suspend fun getComment(postId: Int) =
        RetrofitClient.commentApi.getComment(postId)

    suspend fun addComment(postId: Int, request: AddCommentRequest) =
        RetrofitClient.commentApi.addComment(postId, request)

    suspend fun removeComment(commentId: Int) =
        RetrofitClient.commentApi.removeComment(commentId)
}