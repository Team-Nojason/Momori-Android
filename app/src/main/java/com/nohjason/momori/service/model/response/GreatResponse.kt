package com.nohjason.momori.service.model.response

import com.google.gson.annotations.SerializedName

data class GreatResponse(
    @SerializedName("post_id")
    val postId: Int,

    @SerializedName("count")
    val count: Int
)
