package com.android.whereareyou.core.domain.model.schedule

import com.android.whereareyou.core.domain.model.Friend

data class DailySchedule(
    val dailyScheduleId: Long,
    val weeklyScheduleId: Long,
    val time: String,
    val title: String,
    val friends: List<Friend>,
    val departures: String,
    val arrivals: String
)
