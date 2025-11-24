package com.spark11e.bot.telegram

@Retention(value = AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
public annotation class BotCommand(
    val command: String
)