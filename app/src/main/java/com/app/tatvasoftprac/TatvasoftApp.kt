package com.app.tatvasoftprac

import android.app.Application
import com.app.tatvasoftprac.ktx.Logger
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@HiltAndroidApp
class TatvasoftApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.d("TatvasoftApp : onCreate()")
    }
}