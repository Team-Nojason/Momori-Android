package com.nohjason.momori.service.repository.great

import com.nohjason.momori.service.RetrofitClient

object GreatRepository {
    suspend fun getGreat(postId: Int) =
        RetrofitClient.greatApi.getGreat(postId)

    suspend fun addGreat(postId: Int) =
        RetrofitClient.greatApi.addGreat(postId)

    suspend fun removeGreat(postId: Int) =
        RetrofitClient.greatApi.removeGreat(postId)
}