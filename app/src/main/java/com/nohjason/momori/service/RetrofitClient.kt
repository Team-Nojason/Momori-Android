package com.nohjason.momori.service

import com.google.gson.GsonBuilder
import com.nohjason.momori.service.api.UserAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://localhost:3000/api/v1/"

    // json to data class
    private val gson = GsonBuilder().setLenient().create()

    // httpClient
    private val okHttpClient= OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

    // retrofit 객체
    private val retrofit = Retrofit.Builder()
        .baseUrl("base_url")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    // api 들... (우리가 쓸 거)
    val userAPI by lazy { retrofit.create(UserAPI::class.java) }

}