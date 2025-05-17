package com.vav.KotlinInAction.Chapter14

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   While Launch coroutine builder does not return anything async coroutine builder
        returns an object of type Deferred<T>.
    *   With an object of Deferred type we can call await{} suspending function to collect results
        of the coroutine.
    *   Await will await the results of the suspending function passed to async
    *   Awaiting two tasks can be done using await{}. But it dosnt mean we are waiting for them to return simultaneously.
        they will return when they are done.
 */

fun main(){
    runBlocking {
        val deferred1 = async { awaitingFunction(1) }
        val deferred2 = async { awaitingFunction(2) }

        println(deferred1.await())
        println(deferred2.await())
    }
}
//after 1000 ms will print
//Coroutine 1 complete
//after 2000 ms will print. This will happen along with above 1000 because if time sharing.
//Coroutine 2 complete

suspend fun awaitingFunction(coroutineId: Int): String{
    delay(1000.milliseconds * coroutineId)
    return "Coroutine $coroutineId complete"
}