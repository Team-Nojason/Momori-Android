package com.nohjason.momori.service.model.request

import com.google.gson.annotations.SerializedName

data class AddCommentRequest(
    @SerializedName("comment")
    val comment: String,

    @SerializedName("user_id")
    val userId: Int
)
