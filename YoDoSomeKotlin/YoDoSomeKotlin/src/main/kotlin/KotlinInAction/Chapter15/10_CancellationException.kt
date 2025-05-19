package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/*
    *   Coroutines when canceled throw a CancellationException at specific points
    *   These specific points are where there is a suspend function called it can throw CancellationException
    *   Not all suspend functions throw CancellationException. Only those who call a suspend function like delay()
        throw this exception.
    *   This is called Cooperative Cancellation. If your suspend function function1 call functions like delay(), yield()..
        then it Cooperates in the cancellation.
    *   The other way to make a suspend function cooperative is by checking in that function:
            while (currentCoroutineContext().isActive) { //do work

    *   Another method Kotlin provides is ensureActive()
        ensureActive() will check if coroutine is active, if not then it will throw CancellationException
        You can call ensureActive() while doing some work.


        IMPT:
    *   COROUTINE USES THIS EXCEPTION TO PROPAGATE CANCELLATION OF OTHER
        COROUTINES SO WE SHOULD NOT HANDLE IT OURSELVES

    *   We have to be cautious with parent exceptions of CancellationException too because if we catch them then
        we might accidentally catch CancellationException and coroutine will never get canceled.
 */


//In case of below example we will either print just A if a CancellationException is thrown
//or it will print ABC if everything goes well
fun main20(){
    runBlocking {
        coroutineScope {
            print("A")
            delay(1000.milliseconds)
            print("B")
            print("C")
        }
    }
}
//ABC


//So our main method is going to cancel the coroutine after 2 seconds
//doWork() does some work every 500ms
//but doWork() also throws an exception
//now in withTimeoutOrNull we added a try catch to catch above exception but we
//added catch(e: Exception) which catches all the exception including CancellationException
//So now after 2 seconds suspend function doWork() will throw CancellationException which will accidentally get caught
//because of catch block
//now our coroutine will never get canceled
//and it will keep printing
fun main(){
    runBlocking {
        withTimeoutOrNull(2.seconds){
            while (true){
                try {
                    doWork()
                }catch (e: Exception){
                    println("OOPS ${e.message}")
                }
            }
        }
    }
}

suspend fun doWork(){
    delay(500.milliseconds)
    throw UnsupportedOperationException("Didnt work")
}

//OOPS Timed out waiting for 2000 ms
//OOPS Timed out waiting for 2000 ms
//OOPS Timed out waiting for 2000 ms
//OOPS Timed out waiting for 2000 ms
//OOPS Timed out waiting for 2000 ms
//OOPS Timed out waiting for 2000 ms
//......
//......