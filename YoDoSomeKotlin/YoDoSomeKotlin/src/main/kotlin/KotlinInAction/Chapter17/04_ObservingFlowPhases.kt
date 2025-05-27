package com.vav.KotlinInAction.Chapter17

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/*
    *   Flows have phases like onStart, onEach, onCompletion and onEmpty
    *   All these methods are intermediate stage methods. None of them are terminal.
    *   Method order matters.
    *   We can hook into these phases and perform some action when a phase is achieved
    *   Notice that you still have to collect the flow. A terminal operator is required for cold flows.
    *   If we emit during any of the phase methods then value will be added to the queue
 */

fun main() {
    val flow = flow<Int?> {
        emit(20)
        emit(30)
        emit(40)
        emit(null)
        emit(60)
        emit(70)
    }

    val flow2 = flow<Int> {

    }

    runBlocking {
        flow.onStart {
            println("Emission started!")
        }.onEmpty {
            println("No value!")
            emit(404)
        }.onEach {
            println("current value: $it")
        }.onCompletion { cause ->
            cause?.let { println("Error cause $cause") }
            println("Flow ended!")
        }.collect()

        flow2.onEach {
            println("current value: $it")
        }.onEmpty {
            println("No value!")
            emit(404)
        }.onCompletion { cause ->
            cause?.let { println("Error cause $cause") }
            println("Flow 2 ended!")
        }.collect {
            println(it)
        }
    }

}

//Emission started!
//current value: 20
//current value: 30
//current value: 40
//current value: null
//current value: 60
//current value: 70
//Flow ended!

//No value!
//current value: 404
//404
//Flow 2 ended!