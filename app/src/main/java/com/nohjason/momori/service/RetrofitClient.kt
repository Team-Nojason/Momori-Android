package com.nohjason.momori.service

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.nohjason.momori.BuildConfig
import com.nohjason.momori.service.api.auth.PostAPI
import com.nohjason.momori.service.api.auth.AuthAPI
import com.nohjason.momori.service.interceptor.LoginInterceptor
import okhttp3.OkHttpClient
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
    private val interceptor = LoginInterceptor()

    // httpClient
    private val okHttpClient= OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    // retrofit 객체
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    // api 들... (우리가 쓸 거)
    val authAPI by lazy { retrofit.create(AuthAPI::class.java) }
    val postAPI by lazy { retrofit.create(PostAPI::class.java) }

}