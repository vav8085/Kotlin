package com.vav.KotlinInAction.Chapter14

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.milliseconds

/*
    withContext is used to suspend the execution of the parent coroutine when we want to
    do some child operation.
    e.g. Block the current coroutine until network call is complete.

    Notice that if we launch another coroutine then it wont block the parent coroutine
    unless its another runBlocking.

    Also notice that writing 2 runBlocking will do the same but withContext allows us to
    switch the dispatcher type
    e.g.
    Load some data in IO dispatcher
    withContext(Main) to display current progress
    Load some more data in IO dispatcher
 */

fun main11(){
    runBlocking {
        println("hello from runblocking")
        launch {
            delay(2000.milliseconds)
            println("hello from launch")
        }
        println("runblocking ends")
    }
}
//hello from runblocking
//runblocking ends
//2000 ms wait
//hello from launch

fun main12(){
    runBlocking {
        println("hello from runblocking")
        withContext(Dispatchers.Default) {
            delay(2000.milliseconds)
            println("hello from launch")
        }
        println("runblocking ends")
    }
}
//hello from runblocking
//2000 ms wait
//hello from launch
//runblocking ends


fun main(){
    runBlocking {
        println("hello from runblocking")
        runBlocking {
            delay(2000.milliseconds)
            println("hello from runblocking 2")
        }
        println("runblocking ends")
    }
}
//hello from runblocking
//hello from runblocking 2
//runblocking ends