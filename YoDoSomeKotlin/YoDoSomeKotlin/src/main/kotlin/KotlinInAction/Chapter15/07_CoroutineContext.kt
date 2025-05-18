package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.*

/*
    *   Lets check how CoroutineContext is used in Structured Concurrency.
    *   Just like we discussed that when a child coroutine is created it uses the same context of the parent
        but creates a new job
    *   As we can see in below example first launch inherits the dispatcher from parent runBlocking
    *   While the third launch overrides the dispatcher from parent launch.
    *   So we can see that a new coroutine created will run on its parents dispatcher.
    *   Job object has a parent property
 */

fun main19(){
    runBlocking(Dispatchers.Default) {
        println(coroutineContext)
        launch {
            println(coroutineContext)
            launch(Dispatchers.IO + CoroutineName("hello")){
                println(coroutineContext)
            }
        }
    }
}

//[BlockingCoroutine{Active}@19a81d18, Dispatchers.Default]
//[StandaloneCoroutine{Active}@554fded0, Dispatchers.Default]
//[CoroutineName(hello), StandaloneCoroutine{Active}@676cc730, Dispatchers.IO]


//You can also get all children of a coroutine
fun main(){
    runBlocking(CoroutineName("A")) {
        println("A ${coroutineContext.job}")
        launch (CoroutineName("B")) {
            println("B ${coroutineContext.job}")
            println("B s parent ${coroutineContext.job.parent}")
        }
        println("A's child ${coroutineContext.job.children.first()}")
    }
    println()
}
//A BlockingCoroutine{Active}@383534aa
//A's child StandaloneCoroutine{Active}@567d299b
//B StandaloneCoroutine{Active}@567d299b
//B s parent BlockingCoroutine{Completing}@383534aa