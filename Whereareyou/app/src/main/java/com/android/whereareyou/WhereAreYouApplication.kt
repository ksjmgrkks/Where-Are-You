package com.android.whereareyou

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.android.whereareyou.core.util.preference.PreferenceHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhereAreYouApplication : Application() {
    companion object {
        var prefs: SharedPreferences? = null
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        prefs = base?.let { PreferenceHelper.defaultPrefs(it) }
    }
}