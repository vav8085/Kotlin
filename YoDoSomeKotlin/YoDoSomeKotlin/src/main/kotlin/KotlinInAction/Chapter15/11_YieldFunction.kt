package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Notice that a coroutine functions suspend and share time. While one is suspended the other will do some work
    *   Suspension happens whenever we call a suspend function in a coroutine and while this function is waiting for
        some reason like delay() or getting network response, other coroutine can work meanwhile.
    *   If we call a non suspend function from out our coroutine and it does some work, then even if its waiting for some
        response, our coroutine will not be aware of it and our coroutine will be working.
    *   Notice that coroutines share time only when one of them is waiting or suspended.
    *   WHEN ONE COROUTINE YIELD FOR THE OTHER THEN IT TELLS THE DISPATCHER THAT I AM DONE WITH SOME WORK AND I WILL
        TAKE A BREAK WHILE OTHER COROUTINES CAN TAKE MY PLACE. DISPATCHER PUTS THIS COROUTINE AT START OF THE QUEUE
        WHILE OTHER COROUTINES CAN FINISH.
 */

//Below will first finish first coroutine and then second coroutine because doCpuHeavyWork() is not suspend function
//and is not waiting. Coroutine 1 thinks that its doing work so it wont let coroutine 2 work until its complete.
fun main21() {
    runBlocking {
        launch {
            repeat(3) {
                doCpuHeavyWork()
                println("coroutine 1 working!")
            }
        }
        launch(Dispatchers.IO) {
            repeat(3) {
                doCpuHeavyWork()
                println("coroutine 2 working!")
            }
        }
    }
}

fun doCpuHeavyWork(): Int {
    var counter = 0
    val startTime = System.currentTimeMillis()
    while (System.currentTimeMillis() < startTime + 500) {
        counter++
    }
    return counter
}
//coroutine 1 working!
//coroutine 1 working!
//coroutine 1 working!
//coroutine 2 working!
//coroutine 2 working!
//coroutine 2 working!

//This will print one by one because while one coroutine is suspended because of delay, other can use the time to do some
//work
suspend fun doCpuHeavyWork1(): Int {
    var counter = 0
    val startTime = System.currentTimeMillis()
    delay(200.milliseconds)
    while (System.currentTimeMillis() < startTime + 500) {
        counter++
    }
    return counter
}

//coroutine 1 working!
//coroutine 2 working!
//coroutine 1 working!
//coroutine 2 working!
//coroutine 1 working!
//coroutine 2 working!


//In this case both coroutines are working on different dispatchers, so they can work without waiting for each other.
fun main() {
    runBlocking {
        launch {
            repeat(3) {
                doCpuHeavyWork4()
                println("coroutine 1 working!")
            }
        }
        launch(Dispatchers.IO) {
            repeat(3) {
                doCpuHeavyWork4()
                println("coroutine 2 working!")
            }
        }
    }
}

fun doCpuHeavyWork3(): Int {
    var counter = 0
    val startTime = System.currentTimeMillis()
    while (System.currentTimeMillis() < startTime + 500) {
        counter++
    }
    return counter
}

//coroutine 2 working!
//coroutine 1 working!
//coroutine 1 working!
//coroutine 2 working!
//coroutine 2 working!
//coroutine 1 working!


//With Yield they Yield to each other one by one AND GET ADDED TO THE DISPATCHER QUEUE.
suspend fun doCpuHeavyWork4(): Int {
    var counter = 0
    val startTime = System.currentTimeMillis()
    while (System.currentTimeMillis() < startTime + 500) {
        counter++
    }
    yield()
    return counter
}
//coroutine 1 working!
//coroutine 2 working!
//coroutine 1 working!
//coroutine 2 working!
//coroutine 1 working!
//coroutine 2 working!

//Coroutine1 --yeild1--coroutine2--yeild2--coroutine1--yeild1---coroutine2--yeild2--->