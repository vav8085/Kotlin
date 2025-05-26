package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   You used flow builder function to create flows
    *   To add values to the flow you call emit()
    *   You use collect() to iterate values in the flow.

    *   Anyone can push data to a flow. it may not be a suspending function.
    *** To retrieve data from a flow we call collect() which is a suspending function
        so it has be called from a coroutine.
    *   In below example we can see that flows dont wait for entire createFlow() to complete
        they return values as they become available.
 */


fun main() {
    val flowValue = createFlow()
    runBlocking {
        flowValue.collect {
            println(it)
        }
    }
}

fun createFlow(): Flow<Int> {
    return flow {
        emit(1)
        delay(1000.milliseconds)
        emit(2)
        delay(1000.milliseconds)
        emit(3)
        delay(1000.milliseconds)
    }
}

//1
//print after delay of 1 seconds each
//2
//3

