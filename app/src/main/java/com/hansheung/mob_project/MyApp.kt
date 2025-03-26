package com.hansheung.mob_project

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidApp
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

    }

}