package com.android.whereareyou.core.presentation.model.mapper

import com.android.whereareyou.core.domain.model.schedule.WeeklySchedule
import com.android.whereareyou.core.presentation.model.schedule.weekly.UIWeeklySchedule
import javax.inject.Inject

class WeeklyScheduleUiMapperImpl @Inject constructor() : UiMapper<WeeklySchedule, UIWeeklySchedule> {

    override fun mapToView(input: WeeklySchedule): UIWeeklySchedule {
        return UIWeeklySchedule(
            date = input.date,
            day = input.day,
            title = input.title
        )
    }
}
