package com.android.whereareyou

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.android.whereareyou.core.util.preference.PreferenceHelper
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.KakaoJson.base
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhereAreYouApplication : Application() {
    companion object {
        lateinit var application: WhereAreYouApplication
        lateinit var prefs: SharedPreferences
        const val NATIVE_APP_KEY = "019737c35bd5581de03ff2046d6b6203"
    }
    init {
        application = this
    }

    override fun onCreate() {
        super.onCreate()
        // Kakao SDK 초기화
        KakaoSdk.init(this, NATIVE_APP_KEY)
        // SharedPreferences 초기화
        prefs = applicationContext?.let { PreferenceHelper.defaultPrefs(it) }!!
    }
}