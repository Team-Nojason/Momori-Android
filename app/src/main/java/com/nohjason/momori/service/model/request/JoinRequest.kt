package com.nohjason.momori.service.model.request

import com.google.gson.annotations.SerializedName

data class JoinRequest(
    @SerializedName("id_token")
    val idToken: String,

    @SerializedName("profile_url")
    val profileUrl: String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("platform_type")
    val platformType: String,

    @SerializedName("fcm_key")
    val fcmKey: String
)