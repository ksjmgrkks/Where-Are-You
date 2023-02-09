package com.android.whereareyou.core.data.cache.dao

import androidx.room.*
import com.android.whereareyou.core.data.cache.model.schedule.CachedDailySchedule
import com.android.whereareyou.core.data.cache.model.schedule.CachedWeeklySchedule
import io.reactivex.Flowable

@Dao
abstract class DailyScheduleDao {

    @Transaction
    @Query("SELECT * FROM dailySchedule")
    abstract fun getDailySchedule(): Flowable<List<CachedDailySchedule>>
}