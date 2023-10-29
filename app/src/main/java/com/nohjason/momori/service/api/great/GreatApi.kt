package com.nohjason.momori.service.api.great

import com.nohjason.momori.service.model.response.GreatResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GreatApi {
    @GET("/great/{post_id}")
    suspend fun getGreat(
        @Path("post_id") postId: Int
    ): GreatResponse

    @POST("/great/{post_id}")
    suspend fun addGreat(
        @Path("post_id") postId: Int
    ): GreatResponse

    @DELETE("/great/{post_id}")
    suspend fun removeGreat(
        @Path("post_id") postId: Int
    ): GreatResponse
}