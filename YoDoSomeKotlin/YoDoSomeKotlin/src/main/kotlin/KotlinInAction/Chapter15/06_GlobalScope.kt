package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   GlobalScope is a top level CoroutineScope
    *   Coroutines defined in a GlobalScope are not tied up to their parent or other coroutines. they run in globalscope.
    *   Coroutines defined in a GlobalScope run for the entire duration of the application.
    *   GlobalScope break the structured concurrency.
    *   Coroutines running in a GlobalScope keep running after parent coroutine ins canceled.
    *   GlobalScope can cause memory leak when a coroutine keeps holding memory of a child object even after its lifecycle ends.
    *   We will get a warning when using GlobalScope @delicateCoroutinesApi
    *   GlobalScope is rarely used only when we need a coroutine running for the duration of the application in the background.
 */

//Below method will only print "runblocking done" because it does not define the lifecycle of child coroutines and
//is nowhere tied to it. GlobalScope breaks structured concurrency
fun main18(){
    runBlocking {
        val job1 = GlobalScope.launch {
            delay(1000.milliseconds)
            println("first global coroutine done")
            launch {
                delay(250.milliseconds)
                println("child coroutine done")
            }
        }
        job1.join()
        GlobalScope.launch {
            delay(500.milliseconds)
            println("second global coroutine done")
        }
        println("runblocking done")
    }
}
//runblocking done


//we have to explicitly wait for job1 but still there is no parent child relationship between runBlocking and job1
fun main17(){
    runBlocking {
        val job1 = GlobalScope.launch {
            delay(1000.milliseconds)
            println("first global coroutine done")
            launch {
                delay(250.milliseconds)
                println("child coroutine done")
            }
        }
        job1.join()
        GlobalScope.launch {
            delay(500.milliseconds)
            println("second global coroutine done")
        }
        println("runblocking done")
    }
}
//first global coroutine done
//child coroutine done
//runblocking done

//With a thread.sleep() we can see that "second global coroutine" has chances to execute if application waits for it
fun main(){
    runBlocking {
        val job1 = GlobalScope.launch {
            delay(1000.milliseconds)
            println("first global coroutine done")
            launch {
                delay(250.milliseconds)
                println("child coroutine done")
            }
        }
        job1.join()
        GlobalScope.launch {
            delay(500.milliseconds)
            println("second global coroutine done")
        }
        println("runblocking done")
        Thread.sleep(1000)
    }
}
//first global coroutine done
//child coroutine done
//runblocking done
//second global coroutine done