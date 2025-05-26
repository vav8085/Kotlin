package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Cold Flows  -   *   create a flow builder flow{}
                        *   emit data from a flow builder using emit()
                        *   collect data in a coroutine using collect{}

    *   NOTE:   FLOW BUILDER TAKES A BLOCK WITH A SUSPEND MODIFIER WHICH ALLOWS CALLING OTHER SUSPEND FUNCTIONS LIKE DELAY

    *   Cold flows are inert
    *   They are not executed until a terminal operator is added to them.
    *   Between the builder and the terminal operator there can be other operations
    *   A terminal operation is what kicks off the flow here.
    *   Because the flow builder does not execute until the terminal operator, they can be built outside
        a suspend function or coroutine.
    *   But notice that network calls/ delays can only be added to a flow builder which is a suspend block
    *   Cold flows can be used to create an infinite flow because they are not executed until collect is called.
    *   Collect{} is itself suspending because it should be able to suspend while some background work is going on.

    *  A simple way to look at this:
       -    call flow{} from anywhere suspend / normal code
       -    inside flow{} call suspend or normal functions to create a computation/ delayed operations or network call
       -    emit values using emit{}
       -    after flow{} perform some other operations
       -    call collect{} from a coroutine and retrieve values.

    *  Internal working:
        -   Internally we see that flow {} has a param a lambda with reciever type as FlowCollector which means we can call all methods of FlowCollector in flow{} block
            flow(@BuilderInference block: suspend FlowCollector<T>.() -> Unit)
        -   FlowCollector has a method emit() which you can call inside flow{}
        -   now calling flow{} is basically setting up what should happen inside the lambda suspend FlowCollector<T>.() -> Unit but its not executed yet
        -   What can execute this lambda? any instance of FlowCollector.
        -   Flow has a method collect() with one parameter FlowCollector<T>
        -   So when we call collect() we pass it an instance of FlowCollector
        -   Whenever this instance of FlowCollector is created the lambda defined in flow{} is called on that instance and values are emitted
        -   so basically the lambda in collect is body of emit() method.


     * Execution:
        -   Now we know the internal working its easy to understand execution
        -   The execution starts with the collect() when an instance of FlowCollector is created. creation of FlowCollector runs the flow {} block
            on that FlowCollector instance calling its various methods. This suspends execution of coroutine running collect()
        -   Whenever an emit() is called the collect{} block is executed on that instance of FlowCollector
        -   emit can be called many times inside flow{} and the flow{} block suspends its execution until collect{} block completes.
        -   This keep happening until flow{} block completes and nothing more is emitted.


     *  Basically its a dance between flow{} and collect{} suspending blocks that helps to handle backpressure.

     * NOTE - CALLING COLLECT MULTIPLE TIMES WILL LEAD TO EXECUTION OF FLOW{} BLOCK AGAIN. IF FLOW{} BLOCK HAS ANY SIDE EFFECTS THEN THEY WILL HAPPEN EVERY TIME.
 */

fun main(){
    runBlocking {
        emitLetters().collect { it: Char -> println("$it Collected!") }
        createCounter().collect{
            println(it)
        }
    }
}

private fun emitLetters() = flow<Char> {
    println("Emitting A")
    emit('A')
    delay(1000.milliseconds)

    println("Emitting B")
    emit('B')
}

//Emitting A
//A Collected!
//1000 milliseconds wait
//Emitting B
//B Collected!

private fun createCounter() = flow<Int> {
    var x = 0
    while (true) {
        emit(x++)
        delay(200.milliseconds)
    }
}
//0
//1
//2
//3
//4
//5
//6
//7
//....