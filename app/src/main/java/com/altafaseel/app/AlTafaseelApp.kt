package com.altafaseel.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlTafaseelApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}

