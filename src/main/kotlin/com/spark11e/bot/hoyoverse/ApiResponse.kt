package com.spark11e.bot.hoyoverse

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiResponse<T>(
    @JsonProperty("retcode") val retcode: Int,
    @JsonProperty("message") val message: String,
    @JsonProperty("data") val data: T?
)
