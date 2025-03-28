package com.oguzhanozgokce.finishmarmarab2b

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FinishMarmaraB2B : Application() {
    override fun onCreate() {
        super.onCreate()
        require(BuildConfig.BASE_URL.isNotEmpty()) { "BASE_URL is not set!" }
    }
}
