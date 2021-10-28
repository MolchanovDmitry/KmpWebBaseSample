package com.example.playermodels

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Эмулятор провайдера, который отдает url после каких-то асинхронных телодвижений.
 */
class FakeProvider {

    /** Вернет ссылку после задержки. */
    suspend fun load(): String = withContext(Dispatchers.Default) {
        println("fake loading")
        delay(1000)
        "https://clck.ru/YQb6f"
    }

}