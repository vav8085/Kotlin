package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Establishes hierarchy between coroutines.
    *   Gives control over coroutine execution and cancellation.
    *   E.g. if you are on a mobile screen and you move away from that screen then you might want to
        cancel any pending network calls. For this its important to keep track of any coroutines started.
        These coroutines can then be canceled.
 */

/*
    *   With structured concurrency, each coroutine belongs to a Coroutine Scope
    *   Coroutine scopes help establishing parent-child relationship between coroutines.
    *   launch and async functions are extension functions of CoroutineScope interface.
    *   Whenever we use launch and async we create a child coroutine inside some parent coroutine.
    *   In structured concurrency, parent and children coroutines are aware of each other.
        If parent is done with its work it still waits for its children to get completed befre returning.
    *   This also enables us to cancel children coroutine if parent gets canceled.
 */

//In this example even if parent is executed first, its children are still processing.
//this means parent will not return yet. It will wait until all children are complete.
fun main(){
    runBlocking {
        launch {
            delay(1.milliseconds)
            launch {
                delay(250.milliseconds)
                println("grandchild complete")
            }
            println("child 1 complete")
        }
        launch {
            delay(500.milliseconds)
            println("child 2 complete")
        }
        println("parent complete")
    }
}
//parent complete
//child 1 complete
//grandchild complete
//child 2 complete