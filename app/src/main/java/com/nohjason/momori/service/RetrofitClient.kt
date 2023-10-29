package com.nohjason.momori.service

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.nohjason.momori.BuildConfig
import com.nohjason.momori.service.api.post.PostApi
import com.nohjason.momori.service.api.auth.AuthApi
import com.nohjason.momori.service.interceptor.LoginInterceptor
import com.nohjason.momori.util.Json.isJsonArray
import com.nohjason.momori.util.Json.isJsonObject
import com.nohjason.momori.util.TAG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit



object RetrofitClient {
    // json to data class
    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, JsonDeserializer { json, _, _ ->
            LocalDateTime.parse(json.asString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
        })
        .registerTypeAdapter(LocalDate::class.java, JsonDeserializer { json, _, _ ->
            LocalDate.parse(json.asString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        })
        .registerTypeAdapter(LocalTime::class.java, JsonDeserializer { json, _, _ ->
            LocalTime.parse(json.asString, DateTimeFormatter.ofPattern("HH:mm:ss.SSS'Z'"))
        })
        .setLenient()
        .create()

    // loginInterceptor
    private val loginInterceptor = LoginInterceptor()
    private val logInterceptor = HttpLoggingInterceptor { message ->
        Log.i(TAG, "Retrofit-Client : $message")

        when {
            message.isJsonObject() ->
                Log.i(TAG, JSONObject(message).toString(4))

            message.isJsonArray() ->
                Log.i(TAG, JSONObject(message).toString(4))

            else -> {
                try {
                    Log.i(TAG, JSONObject(message).toString(4))
                } catch (e: Exception) {
                    Log.i(TAG, message)
                }
            }
        }
    }.setLevel(HttpLoggingInterceptor.Level.BODY)


    // httpClient
    private val okHttpClient= OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .addInterceptor(loginInterceptor)
        .addInterceptor(logInterceptor)
        .build()

    // retrofit 객체
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    // api 들... (우리가 쓸 거)
    val authAPI by lazy { retrofit.create(AuthApi::class.java) }
    val postAPI by lazy { retrofit.create(PostApi::class.java) }

}