package com.nohjason.momori.service.model.response

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class CommentResponse (
    @SerializedName("comment_id")
    val commentId: Int,

    @SerializedName("created_at")
    val createdAt: LocalDateTime,

    @SerializedName("comment")
    val comment: String,

    @SerializedName("post_id")
    val postId: Int,

    @SerializedName("user_id")
    val userId: Int
)