package com.spark11e.bot.config

import com.spark11e.bot.service.TelegramBotService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


@Configuration
private open class BotConfig {

    @Bean
    open fun telegramBotApi(telegramBot: TelegramBotService): TelegramBotsApi {

        val botApi = TelegramBotsApi(DefaultBotSession::class.java)

        try {

            botApi.registerBot(telegramBot)
            println("Telegram Bot ${telegramBot.botUsername} успешно зарегистрирован и запущен.")

        } catch (e: Exception) {

            System.err.println("Ошибка при регистрации Telegram Bot: ${e.message}")
            e.printStackTrace()
        }

        return botApi
    }
}
