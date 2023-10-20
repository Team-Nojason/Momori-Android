package com.nohjason.momori.service.repository.user

import com.nohjason.momori.service.RetrofitClient
import com.nohjason.momori.service.model.request.LoginRequest

object UserRepository {

    suspend fun login(loginRequest: LoginRequest) =
        RetrofitClient.userAPI.login(loginRequest)


}

/**
 *
 *     @SerializedName("id_token")
 *     val idToken: String,
 *
 *     @SerializedName("email")
 *     val email: String,
 *
 *     @SerializedName("nickname")
 *     val nickname: String,
 *
 *     @SerializedName("profile_url")
 *     val profileUrl: String,
 *
 *     @SerializedName("platform_type")
 *     val platformType: String
 */