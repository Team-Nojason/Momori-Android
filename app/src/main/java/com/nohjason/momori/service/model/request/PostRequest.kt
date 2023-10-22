package com.nohjason.momori.service.model.request

import com.google.gson.annotations.SerializedName

data class PostRequest(
    @SerializedName("content")
    val content: String,

    @SerializedName("latitude")
    val latitude: Float,

    @SerializedName("longitude")
    val longitude: Float,

    @SerializedName("is_public")
    val isPublic: Boolean
)