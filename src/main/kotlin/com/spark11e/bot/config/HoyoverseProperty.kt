package com.spark11e.bot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "hoyoverse")
public data class HoyoverseProperty(
    val honkaiStarRailUrl: String = "https://enka.network/api/hsr/uid/",
    val genshinImpactUrl: String = "https://enka.network/api/uid/",
    val zenlessZoneZeroUrl: String = "https://enka.network/api/zzz/uid/"
)