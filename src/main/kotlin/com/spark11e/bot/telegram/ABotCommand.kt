package com.spark11e.bot.telegram

@Retention(value = AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.FUNCTION])
annotation class BotCommand(
    val command: String
)