package com.spark11e.bot.repository

import com.spark11e.bot.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByNickname(nickname: String): User?
}