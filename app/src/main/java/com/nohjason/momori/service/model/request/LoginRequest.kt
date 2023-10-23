package com.nohjason.momori.service.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id_token")
    val idToken: String,

    @SerializedName("platform_type")
    val platformType: String
)
