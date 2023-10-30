package com.nohjason.momori.service.repository.post

import com.nohjason.momori.service.RetrofitClient
import com.nohjason.momori.service.model.request.AddPostRequest

object PostRepository {

    suspend fun getPostById(postId: Int) =
        RetrofitClient.postApi.getPostById(postId)

    suspend fun getPostByLocation(latitude: Float, longitude: Float) =
        RetrofitClient.postApi.getPostByLocation(latitude, longitude)

    suspend fun getPostByUser(userId: Int?) =
        RetrofitClient.postApi.getPostByUser(userId)

    suspend fun addPost(request: AddPostRequest, postId: Int) =
        RetrofitClient.postApi.addPost(request, postId)
}