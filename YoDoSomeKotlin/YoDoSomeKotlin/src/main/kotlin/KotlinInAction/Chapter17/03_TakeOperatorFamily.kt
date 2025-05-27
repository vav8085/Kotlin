package com.vav.KotlinInAction.Chapter17

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking

/*
    *   Take operator will cancel the upstream flow when condition is no longer valid
 */

fun main() = runBlocking {
    val flow = flow<Int> {
        emit(20)
        emit(30)
        emit(40)
        emit(50)
        emit(60)
        emit(70)
    }
    val flag = true
    flow.takeIf { flag }?.collect{
        println("value is $it")
    }
    //value is 20
    //value is 30
    //value is 40
    //value is 50
    //value is 60
    //value is 70

    flow.takeWhile { it < 40 }.collect{
        println("value is $it")
    }
    //value is 20
    //value is 30

    flow.take(3).collect{
        println(it)
    }
    //20
    //30
    //40
}
