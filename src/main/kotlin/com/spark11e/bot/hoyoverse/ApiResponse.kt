package com.spark11e.bot.hoyoverse

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val detailInfo: DetailInfo,
    val ttl: Int,
    val uid: String
)

@JsonClass(generateAdapter = true)
data class RecordInfo(
    val achievementCount: Int
)
