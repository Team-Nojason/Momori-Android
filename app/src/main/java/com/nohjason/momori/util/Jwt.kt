package com.nohjason.momori.util

import org.json.JSONObject
import java.nio.charset.StandardCharsets
import java.util.Base64

object Jwt {
    fun getPayload(token: String): JSONObject {


        val payload = token.split(".")[1]
        val decodedPayload = String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8)

        // Parse the JSON payload
        val payloadJson = decodedPayload.trim()
        val payloadMap = JSONObject(payloadJson)
        return payloadMap
    }
}