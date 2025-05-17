package com.vav.KotlinInAction.Chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Launch coroutine builder is used to launch start and forget coroutines.
    *   This means we start some function from this and forget about the return value.
    *   What happens to suspended code and how do we return to it?
    *       -   Compiler creates mechanism when we create a suspending function to store its state
    *       -   This state can later be restored
    *   Notice that suspended functions we have seen so far utilize concurrency, not parallelism.
    *   coroutines started kind of take time slices of the same thread.
    *   To run coroutines in parallel we can use a multithreaded dispatcher.
    *   Launch does not return an output but can have side effects like writing to a file.
    *   Launch function returns an object of type Job. Job can be used to cancel a coroutine.
 */

fun main(){
    runBlocking {
        doSomethingSlowly()
        launch(Dispatchers.IO) {
            delay(4000.milliseconds)
            println("child coroutine 1 is finally done")
        }
        launch(Dispatchers.IO) {
            println("child coroutine 2 is done")
        }
        println("runBlocking last line")
    }
    println("Line after run blocking wont execute until its complete!")
}

//runBlocking is done!
//runBlocking last line
//child coroutine 2 is done
//child coroutine 1 is finally done
//Line after run blocking wont execute until its complete!

//NOTICE THAT LINE runBlocking last line AND child coroutine 2 is done CAN CHANGE PLACES. BECAUSE COROUTINE2
//CAN EXECUTE BEFORE CODE GOES TO runBlocking last line. BUT IN MOST CASES IT WILL STAY WITH PARENT COROUTINE
//IF PARENT AND CHILD ARE AT SAME LEVEL.