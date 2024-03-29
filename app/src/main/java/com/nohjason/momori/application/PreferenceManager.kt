package com.nohjason.momori.application

import android.content.Context
import android.content.SharedPreferences
import android.os.FileObserver.ACCESS

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(MOMORI_APP, Context.MODE_PRIVATE)

    var accessToken: String by PreferenceDelegate(ACCESS_TOKEN, "")
    var refreshToken: String by PreferenceDelegate(REFRESH_TOKEN, "")
    var platformType: String by PreferenceDelegate(PLATFORM_TYPE, "")
    var isLogin: Boolean by PreferenceDelegate(IS_LOGIN, false)
    fun deleteAllToken() {
        accessToken = ""
        refreshToken = ""
        platformType = ""
        isLogin = false
    }

    companion object {
        // meta
        private const val MOMORI_APP = "MOMORI_APP"

        // auth
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val PLATFORM_TYPE = "PLATFORM_TYPE"
        private const val IS_LOGIN = "IS_LOGIN"

    }

    private inner class PreferenceDelegate<T>(
        private val key: String,
        private val defaultValue: T
    ) {
        operator fun getValue(thisRef: Any?, property: Any?): T {
            return when (defaultValue) {
                is String -> prefs.getString(key, defaultValue as String) as T
                is Boolean -> prefs.getBoolean(key, defaultValue as Boolean) as T
                is Int -> prefs.getInt(key, defaultValue as Int) as T
                else -> throw IllegalArgumentException("Unsupported preference type")
            }
        }

        operator fun setValue(thisRef: Any?, property: Any?, value: T) {
            when (value) {
                is String -> prefs.edit().putString(key, value as String).apply()
                is Boolean -> prefs.edit().putBoolean(key, value as Boolean).apply()
                is Int -> prefs.edit().putInt(key, value as Int).apply()
                else -> throw IllegalArgumentException("Unsupported preference type")
            }
        }
    }
}
