package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.milliseconds

/*
    *   CoroutineScope is the parent interface of all Coroutine Scopes.
    *   All the objects that act as scopes and launch coroutines must adhere to it.
    *   It has one single property called CoroutineContext
    *   CoroutineContext holds Job and CoroutineDispatcher.
    *   Various concrete classes and objects like GlobalScope, viewModelScope, lifecycleScope implement it.
    *   runBlocking{} has CoroutineScope as its lambda receiver.
    *   coroutineScope{} creates a new child CoroutineScope.
    *   launch{}, async{} and withContext{} operate in a CoroutineContext and also have a CoroutineScope receiver.
        but they create a new job instance.
    *   By default a CoroutineScope with a dispatcher automatically creates a new Job.
 */

/*
    *   How to use?
        -   Any class can implement CoroutineScope and has to implement parameter coroutineContext
        -
 */

class MyCoroutineScope(override val coroutineContext: CoroutineContext) : CoroutineScope{
    fun cancel(){
        coroutineContext.job.cancel()
    }
}

fun main15(){
    runBlocking {
        //we are giving our new scope the same job as runBlocking's coroutineContext. this way
        //runBlocking will hold the main thread while we complete our work using myCoroutineScope
        //notice that we should not use myCoroutineScope directly in main() because main wont wait for
        //myCoroutineScope to complete. only with the help of runBlocking we are able to make main thread wait.
        //val myJob = coroutineContext.job //use runBlocking's context's job
        //the other way to wait is call Thread.wait() instead of runBlocking. But in actual case we may never know
        //how long a coroutine will work so using Thread.wait() in not the right way.
        val myJob = Job(coroutineContext[Job]) // create a child job
        val myContext = Dispatchers.Default + myJob + CoroutineName("Default Coroutine")
        val myCoroutineScope = MyCoroutineScope(myContext)
        myCoroutineScope.launch {
            downloadData()
        }
    }
}
suspend fun downloadData(){
    delay(2000.milliseconds)
    println("download complete!")
}
