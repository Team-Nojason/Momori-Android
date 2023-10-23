package com.nohjason.momori.util

import com.google.gson.Gson
import com.nohjason.momori.service.model.response.etc.ErrorResponse
import retrofit2.HttpException

fun HttpException.toErrorResponse(): ErrorResponse =
    Gson().fromJson(response()!!.errorBody()!!.string(), ErrorResponse::class.java)
