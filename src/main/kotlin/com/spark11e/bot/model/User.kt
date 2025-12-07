package com.spark11e.bot.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "bot_user")
data class User (

    @Id
    val id: Long = 666,

    @Column(nullable = false)
    var nickname: String = "unknown",

    @Column(name = "joined_at", nullable = false)
    val joinedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "profile_photo_id", nullable = true)
    var profilePhotoName: String? = null,

    @Column(name = "hoyoverse_id", nullable = true)
    var hoyoverseId: Long? = null,

    var userPassword: String? = null
)