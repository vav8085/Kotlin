package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

/*
    *   When we want to attach multiple collectors to a cold flow then we have to call collect multiple times on a flow
        The problem with doing above is that it will call the entire flow{} block again for each collect() call. This
        can be problematic in case the work done in the flow{} block is expensive and we dont want to repeat it.
        For e.g. if we are making a network request and want to observe it twice then in case of cold observable
        it has to repeat. Doing repetition multiple times can waste computations.

    *   ShareIn - ShareIn when called on a cold observable turns it into a hot observable. It has to be called from a
                  coroutine because the conversion from cold to hot needs calling flow {} which has to be called
                  from a coroutine.
                - ShareIn takes a second parameter 'started' which defines when the flow should be started
                    *   Started values:
                            -   Eagerly - Started immediately even if there is no subscriber
                            -   Lazily - Starts when first subscriber is attached
                            -   WhileSubscribed - Starts when first subscriber subscribes and cancels when last subscriber
                                                  is detached.
 */

fun getTemperatures() = flow {
    while(true) {
        emit(Random.nextInt(30))
        delay(500.milliseconds)
    }
}

fun main(){
    val temps = getTemperatures()
    runBlocking {
        val tempsHot = temps.shareIn(this, SharingStarted.Lazily)
        launch {
            tempsHot.collect {
                println("First subscriber receives reading: $it")
            }
        }
        launch {
            tempsHot.collect {
                println("Second subscriber receives reading: $it")
            }
        }
    }
}
//both subscribers will receive the same readings

//First subscriber receives reading: 4
//Second subscriber receives reading: 4
//First subscriber receives reading: 10
//Second subscriber receives reading: 10
//First subscriber receives reading: 15
//Second subscriber receives reading: 15