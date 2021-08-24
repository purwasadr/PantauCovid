package com.purwasadr.pantaucovid

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class SukaSukaTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    val callbackflo = callbackFlow {

        trySend("ssss")
        trySend("awd")
        trySend("lambdda")
        awaitClose {

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun flowCallbackTest() = runBlockingTest {
        callbackflo.onEach {
            println(it)
        }.first()
    }
}