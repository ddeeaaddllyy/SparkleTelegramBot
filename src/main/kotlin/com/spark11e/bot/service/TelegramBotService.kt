package com.spark11e.bot.service

import com.spark11e.bot.config.BotCommand
import com.spark11e.bot.config.BotCommands
import com.spark11e.bot.config.BotProperty
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

/**
 * Основной класс Telegram-бота.
 *
 * Аннотация @Component регистрирует его как Spring Bean.
 * Он расширяет TelegramLongPollingBot для получения обновлений через Long Polling.
 */
@Component
class TelegramBotService(
    private val botProperty: BotProperty
) : TelegramLongPollingBot() {

    private val log = LoggerFactory.getLogger(TelegramBotService::class.java)

    override fun getBotToken() = botProperty.token

    override fun getBotUsername() = botProperty.username

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() and update.message.hasText()) {
            val message = update.message
            val chatId = message.chatId.toString()
            val messageText = message.text

            if (messageText.startsWith(prefix = "/")) {
                handleCommand(chatId = chatId, commandText = messageText)

            } else {
                sendMessage(chatId, "Вы сказали: \"$messageText\". Я пока умею только обрабатывать команды.")
            }
        }
    }

    private final fun sendMessage(chatId: String, text: String) {
        val message = SendMessage(chatId, text)
        try {
            execute(message)
        } catch (e: TelegramApiException) {
            log.error("Ошибка при отправке сообщения в чат $chatId: ${e.message}")
        }
    }

    private final fun handleCommand(chatId: String, commandText: String) {
        val parts = commandText.split("\\s+".toRegex(), 2)
        val command = parts[0].toLowerCase()
        val arguments = parts.getOrNull(1)

        val botCommand = BotCommands.entries.find { command.startsWith(it.command.toLowerCase()) }

        val responseText = when (botCommand) {
            BotCommands.START -> "Привет! Я Sparkle=). Используйте /help для списка команд."
            BotCommands.HELP -> getHelpMessage()
            BotCommands.HSR_STATS -> arguments?.let {
                val targetUid = it.trim()
                if (targetUid.length == 9 && targetUid.all { char -> char.isDigit() }){
                    getHsrStatsResponse(targetUid)
                }
            }
            BotCommands.INFO -> "Создан с помощью Kotlin, Spring Boot и telegrambots.\nБот: ${botProperty.name}.\nВерсия: ${botProperty.version}"
            null -> "Неизвестная команда. Используйте /help."
        }

        sendMessage(chatId, responseText)
    }

    @BotCommand("/help")
    public open fun getHelpMessage(): String {
        return "Доступные команды:\n" +
                BotCommands.entries.joinToString("\n") { "${it.command} <- ${it.description}" }
    }

    private val hoyoverseService = HoyoverseService()

    @BotCommand("/")
    public open fun getHsrStatsResponse(uid: String): String{
        val f = hoyoverseService.getHSRUserData(uid)
    }

}