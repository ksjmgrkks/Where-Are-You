package com.android.whereareyou.schedule.presentation.calendar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarScheduleViewModel @Inject constructor(
) : ViewModel() {
    init {

    }
    override fun onCleared() {
        super.onCleared()
    }
}