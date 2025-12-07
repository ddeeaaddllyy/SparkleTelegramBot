package com.spark11e.bot.service

import com.spark11e.bot.model.User
import com.spark11e.bot.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
public open class UserService (
    private val userRepository: UserRepository
){
    @Transactional
    fun createOrLoadUser(userId: Long, userNickname: String): User{
        val existingUser = userRepository.findById(userId)

        return if (existingUser.isPresent) {
            existingUser.get()
        } else {
            val newUser = User(
                id = userId,
                nickname = userNickname.ifBlank { "UnknownOlux_$userId" },
                joinedAt = LocalDateTime.now()
            )
            userRepository.save(newUser)
        }
    }

    @Transactional
    fun updateNickname(userId: Long, newNickname: String): User? {
        val user = userRepository.findById(userId)
        return if (user.isPresent) {
            val updatedUser = user.get().apply {
                this.nickname = newNickname.ifBlank { "UnknownOlux_$userId" }
            }
            userRepository.save(updatedUser)
        } else {
            null
        }
    }

    @Transactional
    fun updateProfilePhoto(userId: Long, photoName: String): User? {
        val user = userRepository.findById(userId)
        return if (user.isPresent) {
            val updatedUser = user.get().apply {
                this.profilePhotoName = photoName
            }
            userRepository.save(updatedUser)
        } else {
            null
        }
    }
}