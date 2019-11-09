package com.cdrussell.coroutines.testing

import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.math.sqrt


class HeavyWorker(private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()) {

    suspend fun heavyOperation(): Long {
        return withContext(dispatchers.default()) {
            delay(2_000)
            return@withContext doHardMaths()
        }
    }

    // waste some CPU cycles
    fun doHardMaths(): Long {
        var count = 0.0
        for (i in 1..100_000_000) {
            count += sqrt(i.toDouble())
        }
        return count.toLong()
    }

}