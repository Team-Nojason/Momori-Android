package com.nohjason.momori.service.model.request

import com.google.gson.annotations.SerializedName

data class EditPostRequest(
    @SerializedName("post_id")
    val postId: Int,

    @SerializedName("content")
    val content: String,

    @SerializedName("latitude")
    val latitude: Float,

    @SerializedName("longitude")
    val longitude: Float,

    @SerializedName("is_public")
    val isPublic: Boolean,

    @SerializedName("user_id")
    val userId: Int
)
