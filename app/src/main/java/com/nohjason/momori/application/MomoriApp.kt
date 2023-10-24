package com.nohjason.momori.application

import android.app.Application
import android.content.Context

class MomoriApp : Application() {

    companion object {
        lateinit var prefs: PreferenceManager
        private lateinit var instance: MomoriApp

        fun getContext(): Context {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}