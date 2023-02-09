package com.android.whereareyou.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiDailySchedule(
    @field:Json(name = "time") val time: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "friends") val friends: List<String>?,
    @field:Json(name = "departures") val departures: String?,
    @field:Json(name = "arrivals") val arrivals: String?
)