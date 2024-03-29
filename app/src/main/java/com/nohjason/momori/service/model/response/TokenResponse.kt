package com.nohjason.momori.service.model.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("refresh_token")
    val refreshToken: String,

    @SerializedName("access_token")
    val accessToken: String
)
