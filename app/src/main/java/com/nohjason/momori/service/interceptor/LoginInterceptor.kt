package com.nohjason.momori.service.interceptor

import android.util.Log
import com.nohjason.momori.application.MomoriApp
import com.nohjason.momori.application.PreferenceManager
import com.nohjason.momori.service.RetrofitClient
import com.nohjason.momori.service.model.request.TokenRequest
import com.nohjason.momori.util.Jwt
import com.nohjason.momori.util.TAG
import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LoginInterceptor : Interceptor {

    companion object {
        val ignorePath = listOf("/auth/login", "/auth/join", "/auth/refresh")
        val ignoreMethod = listOf("POST", "POST", "POST")
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val request = chain.request()
        val path = request.url.encodedPath
        val method = request.method

        val preferencesManager = PreferenceManager(MomoriApp.getContext())

        Log.d(TAG, "LoginInterceptor request $method $path - intercept() called")

        // handle ignore path
        ignorePath.forEachIndexed { index, ignorePath ->
            if (ignorePath == path && ignoreMethod[index] == method) {
                Log.d(TAG, "LoginInterceptor ignore path $ignorePath - intercept() called")
                return chain.proceed(request)
            }
        }

        val refreshToken = preferencesManager.refreshToken
        val accessToken = preferencesManager.accessToken

        if (refreshToken != "") {
            val payloadMap = Jwt.getPayload(refreshToken)

            // Extract the expiration time
            val expirationTime = payloadMap["exp"].toString().toLong()
            val instant = Instant.ofEpochSecond(expirationTime)
            val expirationLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

            Log.d(TAG, "LoginInterceptor - $expirationLocalDateTime intercept() called")

            // refresh
            if (LocalDateTime.now().isAfter(expirationLocalDateTime)) {
                val tokenInfo = RetrofitClient.authApi.refresh(
                    TokenRequest(
                        accessToken = accessToken,
                        refreshToken = refreshToken
                    )
                )
                preferencesManager.accessToken = tokenInfo.accessToken
                preferencesManager.refreshToken = tokenInfo.refreshToken
            }
        }



        // re request
        return proceed(
            request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )

    }
}