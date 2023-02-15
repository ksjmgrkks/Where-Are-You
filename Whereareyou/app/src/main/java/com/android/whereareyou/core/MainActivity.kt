package com.android.whereareyou.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.whereareyou.R
import com.android.whereareyou.core.data.api.interceptor.log.Logger
import com.android.whereareyou.core.util.getForegroundFragment
import com.android.whereareyou.core.util.moveScreen
import com.android.whereareyou.databinding.ActivityMainBinding
import com.android.whereareyou.databinding.FragmentSignInBinding
import com.android.whereareyou.schedule.presentation.daily.DailyScheduleFragment
import com.android.whereareyou.schedule.presentation.weekly.WeeklyScheduleFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = requireNotNull(_binding)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setupUI()
    }

    private fun setupUI(){
        //todo: https://thdev.tech/androiddev/2020/07/13/Android-Fragment-ViewModel-Example/
        //      해당 글을 참고하여 MainActivity안에 있는 fab의 visibility를 Fragment에서 컨트롤하도록 작업하기
        binding.floatingActionButton.setOnClickListener {
            when(getForegroundFragment()){
              is WeeklyScheduleFragment -> { moveScreen(R.id.action_weekly_schedule_to_add_schedule) }
              is DailyScheduleFragment -> { moveScreen(R.id.action_daily_schedule_to_add_schedule) }
              else -> {
                  Logger.d("else: ${getForegroundFragment()?.javaClass}")}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}