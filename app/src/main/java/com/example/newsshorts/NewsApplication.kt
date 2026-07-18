package com.example.newsshorts

import android.app.Application

@HiltAndroidApp
class NewsApplication : Application () {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Coming_inside_onCreate")
    }
    companion object{
        const val TAG = "NewsApplication"

    }
}