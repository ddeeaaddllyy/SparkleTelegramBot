package com.spark11e.bot.hoyoverse

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val detailInfo: DetailInfo,
    val ttl: Int,
    val uid: String
)

@JsonClass(generateAdapter = true)
data class DetailInfo(
    val level: Int,
    val nickname: String,
    val uid: Long,
    val recordInfo: RecordInfo
)

@JsonClass(generateAdapter = true)
data class RecordInfo(
    val achievementCount: Int
)


