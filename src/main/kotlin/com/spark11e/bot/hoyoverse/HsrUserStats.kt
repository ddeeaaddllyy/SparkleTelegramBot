package com.spark11e.bot.hoyoverse

import com.fasterxml.jackson.annotation.JsonProperty

data class HsrUserStats(
    @JsonProperty("UID") val uid: String,
    @JsonProperty("level") val level: Int,
    @JsonProperty("world_level") val equilibriumLevel: Int
)
