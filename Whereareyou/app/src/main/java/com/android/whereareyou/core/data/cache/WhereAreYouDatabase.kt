package com.android.whereareyou.core.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.whereareyou.core.data.cache.dao.DailyScheduleDao
import com.android.whereareyou.core.data.cache.dao.FriendsDao
import com.android.whereareyou.core.data.cache.dao.WeeklyScheduleDao
import com.android.whereareyou.core.data.cache.model.schedule.CachedDailySchedule
import com.android.whereareyou.core.data.cache.model.schedule.CachedWeeklySchedule

@Database(
    entities = [
        CachedWeeklySchedule::class,
        CachedDailySchedule::class
    ],
    //db version
    version = 1
)
abstract class WhereAreYouDatabase : RoomDatabase() {
    abstract fun weeklyScheduleDao(): WeeklyScheduleDao
    abstract fun dailyScheduleDao(): DailyScheduleDao
    abstract fun friendsDao(): FriendsDao
}