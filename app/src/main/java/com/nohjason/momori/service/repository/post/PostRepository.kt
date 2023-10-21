package com.nohjason.momori.service.repository.post

import com.nohjason.momori.service.RetrofitClient
import com.nohjason.momori.service.model.request.PostRequest

object PostRepository {
    suspend fun addPost(content: String) =
        RetrofitClient.postAPI.addPost(PostRequest(content))
}