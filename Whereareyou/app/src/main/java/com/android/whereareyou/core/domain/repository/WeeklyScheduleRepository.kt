package com.android.whereareyou.core.domain.repository

import com.android.whereareyou.core.domain.model.schedule.WeeklySchedule
import io.reactivex.Flowable

interface WeeklyScheduleRepository {
    fun getWeeklySchedule(): Flowable<List<WeeklySchedule>>
    suspend fun storeWeeklySchedule(weeklySchedule: List<WeeklySchedule>)
}