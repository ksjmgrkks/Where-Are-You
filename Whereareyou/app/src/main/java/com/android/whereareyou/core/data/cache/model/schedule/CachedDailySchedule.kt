package com.android.whereareyou.core.data.cache.model.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.whereareyou.core.domain.model.Friend
import com.android.whereareyou.core.domain.model.schedule.DailySchedule

@Entity(tableName = "dailySchedule")
data class CachedDailySchedule(
    @PrimaryKey val dailyScheduleId: Long = 0L,
    val weeklyScheduleId: Long = 0L,
    val time: String,
    val title: String,
    val friends: List<Friend>,
    val departures: String,
    val arrivals: String
) {
    companion object {
        fun fromDomain(domainModel: DailySchedule): CachedDailySchedule {
            return CachedDailySchedule(
                dailyScheduleId = domainModel.dailyScheduleId,
                weeklyScheduleId = domainModel.weeklyScheduleId,
                time = domainModel.time,
                title = domainModel.title,
                friends = domainModel.friends,
                departures = domainModel.departures,
                arrivals = domainModel.arrivals
            )
        }
    }
}
