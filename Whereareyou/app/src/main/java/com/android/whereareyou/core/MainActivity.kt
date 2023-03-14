package com.android.whereareyou.core

import android.animation.ObjectAnimator
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.android.whereareyou.R
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.android.whereareyou.core.util.getForegroundFragment
import com.android.whereareyou.core.util.moveScreen
import com.android.whereareyou.databinding.ActivityMainBinding
import com.android.whereareyou.schedule.presentation.calendar.CalendarScheduleFragment
import com.android.whereareyou.schedule.presentation.daily.DailyScheduleFragment
import com.android.whereareyou.schedule.presentation.weekly.WeeklyScheduleFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = requireNotNull(_binding)
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
//        splashScreen.setKeepOnScreenCondition{ true }
//        lifecycleScope.launch {
//            delay(1000)
//            splashScreen.setKeepOnScreenCondition{ false }
//        }

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}