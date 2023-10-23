package com.nohjason.momori.service.interceptor

import android.util.Log
import com.google.gson.Gson
import com.nohjason.momori.BuildConfig
import com.nohjason.momori.application.MomoriApp
import com.nohjason.momori.application.PreferencesManager
import com.nohjason.momori.service.model.request.TokenRequest
import com.nohjason.momori.service.model.response.TokenResponse
import com.nohjason.momori.util.Jwt
import com.nohjason.momori.util.TAG
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LoginInterceptor : Interceptor {

    companion object {

        val ignorePath = listOf("/users/login", "/users/join")
        val ignoreMethod = listOf("POST", "POST")
    }


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val request = chain.request()
        val path = request.url.encodedPath
        val method = request.method

        val preferencesManager = PreferencesManager(MomoriApp.getContext())

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

        val payloadMap = Jwt.getPayload(refreshToken)

        // Extract the expiration time
        val expirationTime = payloadMap["exp"].toString().toLong()
        val instant = Instant.ofEpochSecond(expirationTime)
        val expirationLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())


        Log.d(TAG, "LoginInterceptor - $expirationLocalDateTime intercept() called")

        // refresh
        val refreshRequestJson = Gson().toJson(
            TokenRequest(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        )
        if (LocalDateTime.now().isAfter(expirationLocalDateTime)) {
            Log.d(TAG, "REFRESH $expirationLocalDateTime")
            val client = OkHttpClient()
            val refreshRequest = Request.Builder()
                .url(BuildConfig.SERVER_URL + "users/refresh")
                .post(refreshRequestJson.toRequestBody("application/json".toMediaType()))
                .build()
            val response = client.newCall(refreshRequest).execute()

            if (response.isSuccessful) {
                val tokenInfoJson = response.body?.string()
                val tokenInfo = Gson().fromJson(tokenInfoJson, TokenResponse::class.java)
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