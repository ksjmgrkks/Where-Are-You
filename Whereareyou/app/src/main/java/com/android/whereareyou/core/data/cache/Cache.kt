package com.android.whereareyou.core.data.cache

import com.android.whereareyou.core.data.cache.model.schedule.CachedWeeklySchedule
import io.reactivex.Flowable

interface Cache {
  fun getWeeklySchedule(): Flowable<List<CachedWeeklySchedule>>
}