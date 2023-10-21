package com.nohjason.momori.service

import com.google.gson.GsonBuilder
import com.nohjason.momori.BuildConfig
import com.nohjason.momori.service.api.user.PostAPI
import com.nohjason.momori.service.api.user.UserAPI
import com.nohjason.momori.service.interceptor.LoginInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    // json to data class
    private val gson = GsonBuilder().setLenient().create()

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
    val userAPI by lazy { retrofit.create(UserAPI::class.java) }
    val postAPI by lazy { retrofit.create(PostAPI::class.java) }

}