package com.android.whereareyou.core.data.repository

import com.android.whereareyou.core.data.api.WhereAreYouApi
import com.android.whereareyou.core.data.cache.Cache
import com.android.whereareyou.core.domain.model.schedule.WeeklySchedule
import com.android.whereareyou.core.domain.repository.WeeklyScheduleRepository
import io.reactivex.Flowable
import javax.inject.Inject

class WeeklyScheduleRepositoryImpl @Inject constructor(
    private val api: WhereAreYouApi,
    private val cache: Cache,
) : WeeklyScheduleRepository {
    override fun getWeeklySchedule(): Flowable<List<WeeklySchedule>> {
        return cache.getWeeklySchedule()
            .distinctUntilChanged()
            .map { weeklySchedule ->
                weeklySchedule.map {
                    it.toWeeklyScheduleDomain()
                }
            }
    }

    override suspend fun storeWeeklySchedule(weeklySchedule: List<WeeklySchedule>) {
    }

}