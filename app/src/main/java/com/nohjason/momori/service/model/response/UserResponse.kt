package com.nohjason.momori.service.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user_id")
    val id: Int,

    @SerializedName("email")
    val email: String,

    @SerializedName("profile_url")
    val profileUrl: String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("platform_type")
    val platformType: String,

)
