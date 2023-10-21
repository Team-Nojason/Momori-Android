package com.nohjason.momori.service.model.request

import com.google.gson.annotations.SerializedName

data class PostRequest(
    @SerializedName("content")
    val content: String
)