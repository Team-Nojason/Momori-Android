package com.nohjason.momori.service.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id_token")
    val idToken: String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("profile_url")
    val profileUrl: String,

    @SerializedName("platform_type")
    val platformType: String,

    @SerializedName("fcm_key")
    val fcmKey: String
)