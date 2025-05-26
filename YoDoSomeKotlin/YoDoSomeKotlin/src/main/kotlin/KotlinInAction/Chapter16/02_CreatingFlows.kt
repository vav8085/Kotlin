package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   You used flow builder flow{} function to create flows
    *   To add values to the flow you call emit()
    *   emit can only be called inside a flow builder
    *   You use collect() to iterate values in the flow.

    *** To retrieve data from a flow we call collect() which is a suspending function
        so it has be called from a coroutine.
    *   In below example we can see that flows dont wait for entire createFlow() to complete
        they return values as they become available.

    *   So emit data from anywhere in a flow builder{} and collect{} in a coroutine
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

