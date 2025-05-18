package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Until now we used coroutine builders to create a coroutine scope.
    *   We saw that runBlocking waits for all the children and grandchildren coroutines to complete.
        and it keeps blocking the thread until everything is complete. This is because runBlocking creates a
        coroutine scope for all the coroutines executed inside it.
    *   Notice that launch and async builders cannot be created directly outside runBlocking or coroutineScope.
        This is because launch and async do not create a new coroutine scope.

    *   coroutineScope - Is used to create a new coroutineScope just like runBlocking does. This is the starting
        point of the structured concurrency in the hierarchy.
    *   Notice that coroutineScope is a suspending function unlike runBlocking. that means it can be used to create
        a hierarchy either directly inside runBlocking or another coroutineScope.
    *   you cannot use coroutineScope directly in main without runBlocking. This is why they say runBlocking connects
        the non-suspending world to coroutines.
    *   Note that coroutineScope will not wait for coroutines created by a custom scope like MyCoroutineScope which is
        implementing CoroutineScope. It only waits for coroutines started directly from it and uses its own context.
 */


//Below code uses a suspending function generateValue() that generates 2 numbers
//with 500 milliseconds interval. We call this function from 2 different await builders inside a coroutineScope
//our coroutineScope waits for both the await to return before it returns the value to sum variable
fun main(){
    runBlocking {
        println("runblocking start")
        computeSum()
        println("runblocking end")
    }
}
suspend fun computeSum(){
    val sum = coroutineScope {
        println("inside coroutineScope")
        val a = async { generateValue() }
        val b = async { generateValue() }
        println("last step coroutineScope")
        a.await() + b.await()
    }
    println("sum is $sum")
}

suspend fun generateValue(): Int{
    delay(500.milliseconds)
    return Random.nextInt(0,10)
}

//runblocking start
//inside coroutineScope
//last step coroutineScope
//sum is 5
//runblocking end