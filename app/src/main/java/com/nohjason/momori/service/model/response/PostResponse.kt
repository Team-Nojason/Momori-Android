package com.nohjason.momori.service.model.response

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class PostResponse(
    @SerializedName("post_id")
    val postId: Int,

    @SerializedName("content")
    val content: String,


    @SerializedName("created_at")
    val createdAt: LocalDateTime,

    @SerializedName("updated_at")
    val updatedAt: LocalDateTime?,

    @SerializedName("latitude")
    val latitude: Float,

    @SerializedName("longitude")
    val longitude: Float,

    @SerializedName("is_public")
    val isPublic: Boolean,

    @SerializedName("user_id")
    val userId: Int
)
