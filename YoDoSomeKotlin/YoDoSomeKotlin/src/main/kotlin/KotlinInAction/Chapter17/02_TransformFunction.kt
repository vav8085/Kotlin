package com.vav.KotlinInAction.Chapter17

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   In case of map we saw that map can operate a lambda on each collection value and return a totally
        different collection as output.
    *   Sometimes we need more than this. Like we want to send multiply emissions. For example sending
        a uppercase and a lowercase for each received character. This is where transform comes into picture.

    *   Transform gives you a FlowCollector receiver in the lambda. As we saw in Cold Flows, FlowCollector has method
        like emit() which can emit values. Thus giving us control over emission. In case of map we dont have control
        over the emission, its 1 to 1.
    *   Thus Transform allows us to do operations like:
        1.  Filtering
        2.  Mapping
        3.  Expanding
        4.  Conditional logic

    *   With transform you can either emit 0, 1 or more values for each input.
 */

fun main() {
    val flow = flow<String> {
        emit("Joe")
        delay(500.milliseconds)
        emit("Miya")
        delay(500.milliseconds)
        emit("Tom")
        delay(500.milliseconds)
        emit("Sasha")
    }
    runBlocking {
        launch {
            val upperLoserCaseFlow = toUpperAndLowerCase(flow)
            upperLoserCaseFlow.collect {
                println(it)
            }
        }

        val numbersFlow = flow<Int> {
            emit(1)
            delay(500.milliseconds)
            emit(2)
            delay(500.milliseconds)
            emit(3)
            delay(500.milliseconds)
            emit(4)
        }

        launch {
            val evenFlow = isEven(numbersFlow)

            evenFlow.collect {
                println(it)
            }
        }
    }
}

fun toUpperAndLowerCase(flow: Flow<String>) = flow.transform<String, String> {
    emit(it.uppercase())
    emit(it.lowercase())
}

//JOE
//joe
//MIYA
//miya
//TOM
//tom
//SASHA
//sasha

fun isEven(flow: Flow<Int>) = flow.transform<Int, Int> {
    if (it % 2 == 0) emit(it)
}

//2
//4