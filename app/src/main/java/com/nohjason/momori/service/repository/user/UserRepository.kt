package com.nohjason.momori.service.repository.user

import com.nohjason.momori.service.RetrofitClient
import com.nohjason.momori.service.model.request.LoginRequest

object UserRepository {

    suspend fun login(loginRequest: LoginRequest) =
        RetrofitClient.userAPI.login(loginRequest)


}