package com.vav.KotlinInAction.Chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
    Dispatchers can be passed to all the 3 coroutine builders
 */

fun main(){
    runBlocking {
        println("Parent coroutine")
        launch(Dispatchers.Default) {
            println("Child coroutine on default dispatcher")
        }
    }
}
//Parent coroutine - this will be called from pain thread
//Child coroutine on default dispatcher - this will be called from one of the threads dispatcher.default thread pool provides