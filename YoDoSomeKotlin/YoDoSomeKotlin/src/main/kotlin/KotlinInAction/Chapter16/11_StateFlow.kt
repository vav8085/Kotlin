package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Sometimes we have some initial value for a state and this state can change.
    *   StateFlow is used to keep track of changes in this state
    *   It could be change in temperature, change in UI state of an application or any other state.

    *   To create a StateFlow we do same thing that we did for creating SharedFlow.
    *   We create a private property of type _stateFlow MutableStateFlow for emitting values and then we create a public
        property stateFlow for subscribing to it.

    *   NOTE:   NOTICE THAT UNLIKE THE EMIT{} METHOD FOR SHARED FLOW, UPDATE{} IS NOT A SUSPENDING FUNCTION. THAT MEANS
                ITS NOT REQUIRED FOR IT TO BE CALLED FROM A COROUTINE

    *   NOTE:   STATE FLOW ALWAYS HAVE A VALUE UNLIKE COLD FLOWS OT SHARED FLOW. THIS IS THE REASON WE CAN ACCESS IT
                DIRECTLY CALLING stateFlow.value ON IT. THIS WILL RETURN THE CURRENT VALUE

    *   NOTE:   NOTICE THAT state.value CAN ALSO BE UPDATED. IT SHOULD NOT BE DONE THOUGH BECAUSE UPDATING VALUE IS
                NOT AN ATOMIC OPERATION. IF MULTIPLE COROUTINES ARE UPDATING VALUE THEN ITS VALUE CAN BE LOST.
                THIS IS WHERE update() COMES INTO PICTURE. USING UPDATE MAKES UPDATING AN ATOMIC OPERATION.

    *   A stateflow only emits a value if its changed. If value is same as previous then no item is emitted. This is called
        equility based conflation.
 */

class ViewCounter {
    private val _state = MutableStateFlow(5)
    val state = _state.asStateFlow()

    fun incrementViewCount() {
        _state.update {
            it + 1
        }
    }

    fun addSubscriber(scope: CoroutineScope) {
        scope.launch {
            state.collect {
                println("Collected: $it")
            }
        }
    }
}

fun main() = runBlocking {
    val viewCounter = ViewCounter()

    viewCounter.incrementViewCount()
    viewCounter.addSubscriber(this)
    delay(500.milliseconds)
    viewCounter.incrementViewCount()
    delay(500.milliseconds)
    viewCounter.incrementViewCount()

    println(viewCounter.state.value)
}

//Collected: 6
//Collected: 7
//8
//Collected: 8