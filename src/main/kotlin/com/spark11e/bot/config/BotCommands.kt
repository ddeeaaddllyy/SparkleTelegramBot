package com.spark11e.bot.config

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
    HSR_STATS(command = "/get_hsr_account", description = "in process")
}