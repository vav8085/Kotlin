package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Whenever we call collect the coroutine calling it suspends its execution until entire collect{} emit{} flow{} cycle is complete.
    *   a flow can produce infinite number of elements and this can cause coroutine calling collect() suspend infinitely.
    *   Thus collection should provide cancellation

    *   How to cancel collection?
        -   Just cancel the coroutine running the collection.
 */

fun main() {
    runBlocking {
        val job = launch {
            try {
                printIntegers().collect{
                    println(it)
                    if(it == 5) this.coroutineContext.job.cancel()
                }
            }catch (e: CancellationException){
                e.printStackTrace()
            }
        }
        job.join()
    }
}

private fun printIntegers(): Flow<Int>{
    return flow<Int> {
        var x = 0
        while (true) {
            emit(x++)
            delay(2000.milliseconds)
        }
    }
}

//0
//1
//2
//3
//4
//5
//kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@5ba23b66