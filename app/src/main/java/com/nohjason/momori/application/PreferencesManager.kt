package com.nohjason.momori.application

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getData(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    var accessToken
        get() = getData("ACCESS_TOKEN")
        set(value) {saveData("ACCESS_TOKEN", value)}

    var refreshToken
        get() = getData("REFRESH_TOKEN")
        set(value) {saveData("REFRESH_TOKEN", value)}

    var platformType
        get() = getData("PLATFORM_TYPE")
        set(value) {saveData("PLATFORM_TYPE", value)}
}