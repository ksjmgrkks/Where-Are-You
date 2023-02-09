package com.android.whereareyou.core.domain.model.schedule

data class DailySchedule(
    val dailyScheduleId: Long,
    val weeklyScheduleId: Long,
    val time: String,
    val title: String,
    val friends: List<String>,
    val departures: String,
    val arrivals: String
)
