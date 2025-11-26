package com.spark11e.bot.telegram

/**
* Enum class для обеспечения более понятной
* логики работы телеграмм бота и обработки каждой
* возможной команды
*/

enum class BotCommands(
    val command: String,
    val description: String
)
{
    START(command = "/start", description = "bot start"),
    HELP(command = "/help", description = "show all commands"),
    INFO(command = "/info", description = "about bot"),
    HSR_STATS(command = "/hoyostats", description = "show you hsr account by UID"),
    USER_ACCOUNT(command = "/account", description = "in process" )
}