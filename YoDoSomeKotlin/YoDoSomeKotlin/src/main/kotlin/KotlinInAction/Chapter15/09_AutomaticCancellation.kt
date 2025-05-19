package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/*
    *   Coroutines can also be automatically canceled
    *   Kotlin coroutines library provide methods like:
        -   withTimeout - Throws an exception when timeout is reached and computation is not complete
        -   withTimeoutOrNull - returns a null when timeout is reached
    *   these methods constrain the computation within time limit.
    *   withTimeout has to be written in try-catch block to handle the thrown TimeoutCancellationException. This exception
        is important to catch because if uncaught it can cancel the parent coroutine as well.
 */

fun main(){
    runBlocking {
        //only given 500ms so it will fail
        val result = withTimeoutOrNull(500.milliseconds){
            calculateSomething()
        }
        println("result1 is $result") //will return null

        val result2 = withTimeoutOrNull(5.seconds){
            calculateSomething()
        }
        println("result2 is $result2")
    }
}

//returns result after 3 seconds
suspend fun calculateSomething(): Int{
    delay(3.seconds)
    return 2* 2
}

//result1 is null
//result2 is 4