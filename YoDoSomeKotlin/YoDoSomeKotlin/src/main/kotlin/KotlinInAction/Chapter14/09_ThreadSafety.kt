package com.vav.KotlinInAction.Chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

/*
    *   Default and IO are both multithreaded dispatchers
    *   This means our coroutine can use multiple threads to run on.
    *   But remember A Single Coroutine always run on a single thread.
    *   This also means that data related to a single coroutine never gets into a synchronization problem.
    *   BUT CHANGING/ READING DATA FROM TWO DIFFERENT COROUTINES IS NOT AS EASY
    *   WHEN MULTIPLE COROUTINE CHANGE THE SAME DATA, VARIOUS COROUTINES END UP IN DUPLICATE CALCULATION
        AND DATA MAY NOT BE PROCESSED RIGHT
 */

//Single Coroutine
fun main13(){
    runBlocking {
        launch(Dispatchers.Default) {
            var x = 0
            repeat(10_000){
                x++
            }
            println(x)
        }
    }
}
//no change in value
//10000


//multiple coroutines 10000
//because multiple coroutines are modifying the same data, count is same
fun main(){
    runBlocking {
        var x = 0
        repeat(10_000) {
            launch(Dispatchers.Default) {
                x++
            }
        }
        delay(1.seconds)
        println(x)
    }
}
//9796


