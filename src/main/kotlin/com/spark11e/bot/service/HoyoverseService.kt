package com.spark11e.bot.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.spark11e.bot.hoyoverse.ApiResponse
import com.spark11e.bot.hoyoverse.HoyolabProperty
import com.spark11e.bot.hoyoverse.HsrUserStats
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import org.springframework.stereotype.Component

@Component
open class HoyoverseService {
    companion object{
        private val client = OkHttpClient()
        private val mapper = ObjectMapper()
    }
    lateinit var hoyolabProperty: HoyolabProperty
    private val cookie: String = hoyolabProperty.hoyolabCookie
    private val honkaiStarRailUrl: String = hoyolabProperty.honkaiStarRailUrl


    fun getHSRUserData(uid: String): HsrUserStats? {
        val requestUrl = honkaiStarRailUrl + uid

        val request = Request.Builder()
            .url(requestUrl)
            .header("Cookie", cookie)
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Неожиданный код ответа: $response")

                val responseBody = response.body?.string() ?: return null

                val apiResponse = mapper.readValue(
                    responseBody,
                    mapper.typeFactory.constructParametricType(ApiResponse::class.java, HsrUserStats::class.java)
                ) as ApiResponse<HsrUserStats>

                if (apiResponse.retcode == 0) {
                    apiResponse.data
                } else {
                    println("API вернул ошибку (${apiResponse.retcode}): ${apiResponse.message}")
                    null
                }
            }
        } catch (e: Exception) {
            println("Ошибка HTTP-запроса или парсинга: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}