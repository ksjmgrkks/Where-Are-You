package com.android.whereareyou.core.domain.model.pagination

import com.android.whereareyou.core.domain.model.schedule.DailySchedule

data class PaginatedDailySchedule(
    val dailySchedules: List<DailySchedule>,
    val pagination: Pagination
)