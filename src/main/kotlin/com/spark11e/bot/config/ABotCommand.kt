package com.spark11e.bot.config

@Retention(value = AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
public annotation class BotCommand(
    val command: String
)