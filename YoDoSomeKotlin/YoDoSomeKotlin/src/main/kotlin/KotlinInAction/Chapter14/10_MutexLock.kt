package com.vav.KotlinInAction.Chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.Duration.Companion.seconds

/*
    *   Mutex lock allows certain sections of coroutine to execute one coroutine at a time.
    *   So multiple coroutines cannot run this code or alter variables simultaneously.
    *   Delay is important below to print right value of x, this is because runBlocking will finish before the internal
        launch and can print value of x which has not fully calculated at that point in time.
    *   Other thing to understand is we can also use AtomicInteger and other thread-safe data structures
    *   Another thing that can be done is incrementing this x on some single threaded dispatcher using withContext
 */

fun main(){
    runBlocking {
        val mutex = Mutex()
        var x = 0
        repeat(10_000){
            launch(Dispatchers.Default) {
                mutex.withLock {
                    x++
                }
            }
        }
        delay(1.seconds)
        println(x)
    }
}

//10000