package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Operates in a broadcast fashion
    *   Messages are emitted even without collectors
    *   All the subscribers receive the mesages once they are sent.

    *   Usually declared in a container class e.g. RadioStation
    *   Usually are private e.g. _messageFlow which can be used to emit values while
        also has a read only publuc version e.g. messageFlow for subscription or emission.
    *   you can use the MutableSharedFlow to emit the value.
    *   Notice that in case of SharedFlow we dont have a builder like a cold flow.
    *   Emission happens independently of weather a subscriber is present or now.
    *   Also its your responsibility to start a coroutine to emit data into a flow.
    *   We can use more than one coroutine to emit data to a flow at the same time.
    *   WE CAN SEE THAT IN BELOW EXAMPLE WHEN WE CALL broadcastMessage() THE BROADCASTING STARTS IMMEDIATELY
        EVEN WITHOUT ANY SUBSCRIBERS.
    *   Subscribers get messages that are emitted after subscription only. older messages are not delivered
        to new subscribers
 */

class RadioStation{
    private val _messageFlow = MutableSharedFlow<Int>()
    val messageFlow = _messageFlow.asSharedFlow()

    fun broadcastMessage(scope: CoroutineScope){
        scope.launch {
            while (true){
                delay(500.milliseconds)
                val number = Random.nextInt()
                println("Emitting $number")
                _messageFlow.emit(number)
            }
        }
    }

    fun addFirstSubscriber(scope: CoroutineScope){
        scope.launch {
            messageFlow.collect{
                println("Message received by first subscriber $it")
            }
        }
    }

    fun addSecondSubscriber(scope: CoroutineScope){
        scope.launch {
            messageFlow.collect{
                println("Message received by second subscriber $it")
            }
        }
    }
}

fun main(){
    val radioStation = RadioStation()
    runBlocking {
        radioStation.broadcastMessage(this)
        delay(1000.milliseconds)
        radioStation.addFirstSubscriber(this)
        delay(500.milliseconds)
        radioStation.addSecondSubscriber(this)
    }
}

//Message will get emitted even without any subscribers
//Emitting -115381481
//Emitting -6902824
//Emitting 1834480462
//Emitting 1822181531

//after adding a collector using radioStation.collectMessage(this)
//subscriber was added after 2000 ms delay so it wont recieve any messages before it

//Emitting 836676460
//Emitting 1353278366
//Emitting -1402394945
//Emitting -1584560960
//Message received -1584560960
//Emitting -1318332544
//Message received -1318332544


//After adding second subscriber
//Emitting -1513811524
//Emitting 1329037609
//Message received by first subscriber 1329037609
//Emitting 1796933746
//Message received by first subscriber 1796933746
//Message received by second subscriber 1796933746
//Emitting 1112560548
//Message received by first subscriber 1112560548
//Message received by second subscriber 1112560548