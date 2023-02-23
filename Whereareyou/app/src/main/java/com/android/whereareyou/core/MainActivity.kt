package com.android.whereareyou.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.android.whereareyou.R
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.android.whereareyou.core.util.getForegroundFragment
import com.android.whereareyou.core.util.moveScreen
import com.android.whereareyou.databinding.ActivityMainBinding
import com.android.whereareyou.schedule.presentation.calendar.CalendarScheduleFragment
import com.android.whereareyou.schedule.presentation.daily.DailyScheduleFragment
import com.android.whereareyou.schedule.presentation.weekly.WeeklyScheduleFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = requireNotNull(_binding)
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        setupUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupUI(){
        binding.floatingActionButton.setOnClickListener {
            when(getForegroundFragment()){
              is WeeklyScheduleFragment -> { moveScreen(R.id.action_weekly_schedule_to_add_schedule) }
              is DailyScheduleFragment -> { moveScreen(R.id.action_daily_schedule_to_add_schedule) }
              is CalendarScheduleFragment -> { moveScreen(R.id.action_calendar_schedule_to_add_schedule) }
              else -> {
                  Logger.d("else: ${getForegroundFragment()?.javaClass}")}
            }
        }
    }


}