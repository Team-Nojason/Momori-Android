package com.nohjason.momori.service.interceptor

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.nohjason.momori.BuildConfig
import com.nohjason.momori.application.MomoriApp
import com.nohjason.momori.application.PreferencesManager
import com.nohjason.momori.service.model.request.RefreshRequest
import com.nohjason.momori.service.model.response.TokenInfoResponse
import com.nohjason.momori.util.TAG
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Base64

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

        val refreshToken = preferencesManager.getData("REFRESH_TOKEN")

        val payload = refreshToken.split(".")[1]
        val decodedPayload = String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8)

        // Parse the JSON payload
        val payloadJson = decodedPayload.trim()
        val payloadMap = payloadJson.removePrefix("{").removeSuffix("}").split(",").associate {
            val (key, value) = it.split(":")
            key.trim() to value.trim()
        }

        // Extract the expiration time
        val expirationTime = payloadMap["exp"]
        val expirationEpochTime = expirationTime?.toLongOrNull() ?: 0
        val expirationLocalDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(expirationEpochTime),
            ZoneOffset.UTC
        )

        Log.d(TAG, "LoginInterceptor - $expirationLocalDateTime intercept() called")

        // refresh
        val currentTime = LocalDateTime.now()
        val refreshRequestJson = Gson().toJson(RefreshRequest(refreshToken = refreshToken))
        if (currentTime.isAfter(expirationLocalDateTime)) {
            Log.d(TAG, "LoginInterceptor refresh!!!! - intercept() called")
            val client = OkHttpClient()
            val refreshRequest = Request.Builder()
                .url(BuildConfig.SERVER_URL + "users/refresh")
                .post(refreshRequestJson.toRequestBody("application/json".toMediaType()))
                .build()
            val response = client.newCall(refreshRequest).execute()

            if (response.isSuccessful) {
                val tokenInfoJson = response.body!!.string()
                val tokenInfo = Gson().fromJson(tokenInfoJson, TokenInfoResponse::class.java)
                preferencesManager.saveData("ACCESS_TOKEN", tokenInfo.accessToken)
                preferencesManager.saveData("REFRESH_TOKEN", tokenInfo.refreshToken)
            } else throw Exception("로그인 하셈")
        }


        // re request
        val accessToken = preferencesManager.getData("ACCESS_TOKEN")

        return proceed(
            request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )
    }
}