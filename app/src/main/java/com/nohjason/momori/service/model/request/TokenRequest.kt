package com.nohjason.momori.service.model.request

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String
)