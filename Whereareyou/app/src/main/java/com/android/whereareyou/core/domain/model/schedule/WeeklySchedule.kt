package com.android.whereareyou.core.domain.model.schedule

data class WeeklySchedule(
    val weeklyScheduleId: Long,
    val date: String,
    val day: String,
    val title: String
)
