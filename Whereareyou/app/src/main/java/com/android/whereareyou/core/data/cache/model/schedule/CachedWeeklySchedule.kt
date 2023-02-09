package com.android.whereareyou.core.data.cache.model.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.whereareyou.core.domain.model.schedule.DailySchedule
import com.android.whereareyou.core.domain.model.schedule.WeeklySchedule

@Entity(tableName = "weeklySchedule")
data class CachedWeeklySchedule(
    @PrimaryKey val weeklyScheduleId: Long = 0L,
    val date: String,
    val day: String,
    val title: String
) {
    companion object {
        fun fromDomain(domainModel: WeeklySchedule): CachedWeeklySchedule {
            return CachedWeeklySchedule(
                weeklyScheduleId = domainModel.weeklyScheduleId,
                date = domainModel.date,
                day = domainModel.day,
                title = domainModel.title
            )
        }
    }

    fun toWeeklyScheduleDomain(): WeeklySchedule {
        return WeeklySchedule(
            weeklyScheduleId = weeklyScheduleId,
            date = date,
            day = day,
            title = title
        )
    }
}