package com.android.whereareyou.core.data.cache.dao

import androidx.room.*
import com.android.whereareyou.core.data.cache.model.schedule.CachedWeeklySchedule
import io.reactivex.Flowable

@Dao
abstract class WeeklyScheduleDao {

    @Transaction
    @Query("SELECT * FROM weeklySchedule")
    abstract fun getWeeklySchedule(): Flowable<List<CachedWeeklySchedule>>
}