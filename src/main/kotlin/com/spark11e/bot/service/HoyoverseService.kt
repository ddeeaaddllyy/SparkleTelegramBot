package com.spark11e.bot.service

import com.spark11e.bot.hoyoverse.ApiResponse
import com.spark11e.bot.hoyoverse.HoyoverseProperty
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.springframework.stereotype.Component
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalCoroutinesApi::class)
public suspend fun Call.await(): Response {

    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                if (continuation.isCancelled) return

                continuation.resumeWithException(e)

            }

            override fun onResponse(call: Call, response: Response) {
                continuation.resume(response)
            }
        })


    }
}

@Component
open class HoyoverseService(
    private val hoyoverseProperty: HoyoverseProperty,
    private val client: OkHttpClient
) {
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private final val honkaiStarRailUrl: String = hoyoverseProperty.honkaiStarRailUrl

    private val responseAdapter = moshi.adapter(ApiResponse::class.java)

    suspend fun fetchHsrStats(uid: String): Result<ApiResponse> {
        val request = Request.Builder()
            .url("${honkaiStarRailUrl}${uid}/?info")
            .build()

        return try {
            client.newCall(request).await().use { response ->

                if (!response.isSuccessful) {
                    // позже сделать отдельную функцию которая будет обрабатывать каждый код ошибки
                    val error = when (response.code) {
                        404 -> "User does not exist"
                        408 -> "Request Timeout"
                        410 -> "Gone"
                        429 -> "Too Many Requests"
                        else -> "Server Error, try later"
                    }
                    return Result.failure(IOException("HTTP Error ${response.code}\n" +
                            "Reason: $error"))
                }
                val jsonBody = response.body?.string()
                val result = jsonBody?.let { responseAdapter.fromJson(it) }

                if (result == null) {
                    Result.failure(Exception("Сервер не смог предоставить никаких данных о пользователе"))
                }
                else {
                    Result.success(result)
                }

            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}