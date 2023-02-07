package com.android.whereareyou.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiWeeklySchedule(
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "link") val linkUrl: String?,
    @field:Json(name = "image") val imageUrl: String?,
    @field:Json(name = "subtitle") val subtitle: String?,
    @field:Json(name = "pubDate") val publishedDate: String?,
    @field:Json(name = "director") val directors: String?,
    @field:Json(name = "actor") val actors: String?,
    @field:Json(name = "userRating") val userRating: String?
)