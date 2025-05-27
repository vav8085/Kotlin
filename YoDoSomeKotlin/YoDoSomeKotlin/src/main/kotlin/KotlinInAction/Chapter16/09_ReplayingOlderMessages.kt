package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

/*
    *   In case of cold flows the flow only starts when a collector is attached. So there is no
        way this collector can miss any messages.
    *   In case of Shared Flows subscribers can attach at later stage and can miss some older emitted items.
    *   If we want our subscribers to also receive older messages then we can use a 'replay' parameter while
        creating a shared flow.
    *   Adding a replay parameter adds a cache to shared flow.
    *   In below example our MutableSharedFlow caches last 5 messages
 */

class RadioStationWithCache {
    private val _messageFlow = MutableSharedFlow<Int>(replay = 5)
    val messageFlow = _messageFlow.asSharedFlow()

    fun broadcastMessage(scope: CoroutineScope) {
        scope.launch {
            while (true) {
                delay(500.milliseconds)
                val number = Random.nextInt()
                println("Emitting $number")
                _messageFlow.emit(number)
            }
        }
    }

    fun addFirstSubscriber(scope: CoroutineScope) {
        scope.launch {
            messageFlow.collect {
                println("Message received by first subscriber $it")
            }
        }
    }
}

fun main(){
    val radioStationWithCache = RadioStationWithCache()

    runBlocking {
        radioStationWithCache.broadcastMessage(this)
        delay(2000.milliseconds)
        radioStationWithCache.addFirstSubscriber(this)
    }
}
//Older messages are also received
//Emitting 1550041121
//Emitting -2030443086
//Emitting -1670358921
//Message received by first subscriber 1550041121
//Message received by first subscriber -2030443086
//Message received by first subscriber -1670358921
//Emitting -856958491
//Message received by first subscriber -856958491
