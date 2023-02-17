package com.android.whereareyou.schedule.presentation.weekly

import androidx.lifecycle.ViewModel
import com.android.whereareyou.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeeklyScheduleViewModel @Inject constructor(
) : BaseViewModel() {
    init {

    }
    override fun onCleared() {
        super.onCleared()
    }
}