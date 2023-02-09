package com.android.whereareyou.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiWeeklySchedule(
    @field:Json(name = "date") val date: String?,
    @field:Json(name = "day") val day: String?,
    @field:Json(name = "title") val title: String?
)