package com.android.whereareyou.core.data.cache

import com.android.whereareyou.core.data.cache.dao.DailyScheduleDao
import com.android.whereareyou.core.data.cache.dao.FriendsDao
import com.android.whereareyou.core.data.cache.dao.WeeklyScheduleDao
import com.android.whereareyou.core.data.cache.model.schedule.CachedWeeklySchedule
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val weeklyScheduleDao: WeeklyScheduleDao,
    private val dailyScheduleDao: DailyScheduleDao,
    private val FriendsDao: FriendsDao,
) : Cache {
    override fun getWeeklySchedule(): Flowable<List<CachedWeeklySchedule>> {
        return weeklyScheduleDao.getWeeklySchedule()
    }
}