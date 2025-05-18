package com.vav.KotlinInAction.Chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   A suspending function can only be called from:
    *   1.  Another suspending function
    *   2.  From a coroutine
 */

/*
    To create a coroutine you have to use a coroutine builder.
    There are a few of them available
        1.  launch
        2.  async
        3.  runBlocking
 */

/*
    runBlocking-
        1.  Used to bridge the regular blocking code with suspending functions.
        2.  Runs a block of code as a coroutine and blocks the current thread until coroutine is complete.
        3.  From the code block / coroutine passed you can call suspending functions.
        4.  ***By bridging regular code with suspending function means we call runBlocking in the main method and then
            call any launch or async builders inside it. This is because runBlocking provides a coroutine scope(later discussed)
            while launch and async either need a coroutine scope or they can be called from runBlocking.
 */


suspend fun doSomethingSlowly(){
    delay(2000.milliseconds)
    println("runBlocking is done!")
}

/*
    Because doSomethingSlowly() is called in runBlocking coroutine builder. It will block current thread
    and any other lines written after this coroutine wont be called until its complete.
    This means runBlocking is good for synchronous operations.

    If we launch another coroutine from runBlocking without explicitly specifying its dispatcher then it will
    continue to use the same thread as runBlocking and will also block thread.

    The good thing is that now runBlocking and the new coroutine are sharing the same thread. This means if
    the new coroutine calls delay() or any other suspend function which blocks the execution,
    then runBlocking coroutine can continue its execution because its sharing the thread.
    ***check runBlocking example 2

    But notice that vice versa doesnt happen. Parent can take child coroutine's blocked execution but child cannot
    execute in the same thread if parent is blocking the thread
    ***check runBlocking example 3

    But lets try changing the dispatcher of the child coroutine to IO thread and check what happens.
    In this case again if parent coroutine is blocked then child coroutine will have to wait.
    ***check runBlocking example 4

    Now lets check if we have 2 different child coroutine and one waits for 4000 ms and other does not.
    Will the second coroutine execute while first is waiting?
    Yes the second can run while first is suspended.
    ***check runBlocking example 5

    Now lets try 2 different runBlocking while one block the main thread.
    In this case because its runBlocking{} the forst one will block thread and second one wont execute because
    thread is blocked.

    So two launch at same level can run together. But 2 runBlocking cannot
    ***check runBlocking example 6



 */
fun main1(){
    runBlocking {
        doSomethingSlowly()
    }
    println("Line after run blocking wont execute until its complete!")
}
//after 2000 ms
//runBlocking is done!
//"Line after run blocking wont execute until its complete!"



//runBlocking example 2 - child coroutine

fun main2(){
    runBlocking {
        launch {
            delay(4000.milliseconds)
            println("child coroutine is finally done")
        }
        doSomethingSlowly()
    }
    println("Line after run blocking wont execute until its complete!")
}

//this will print -

//after 2000 ms
//runBlocking is done!
//after 4000 ms
//child coroutine is finally done
//Line after run blocking wont execute until its complete!


//runBlocking example 3

fun main3(){
    runBlocking {
        doSomethingSlowly()
        launch {
            delay(4000.milliseconds)
            println("child coroutine is finally done")
        }
    }
    println("Line after run blocking wont execute until its complete!")
}

//this will print -
//after 2000 ms
//runBlocking is done!
//after another 4000 ms
//child coroutine is finally done
//Line after run blocking wont execute until its complete!

//runBlocking example 4
fun main4(){
    runBlocking {
        doSomethingSlowly()
        launch(Dispatchers.IO) {
            delay(4000.milliseconds)
            println("child coroutine is finally done")
        }
    }
    println("Line after run blocking wont execute until its complete!")
}
//after 2000ms
//runBlocking is done!
//after additional 4000 ms
//child coroutine is finally done
//Line after run blocking wont execute until its complete!


//runBlocking example 5 - two child coroutines
fun main(){
    runBlocking {
        doSomethingSlowly()
        val job = launch(Dispatchers.IO) {
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
//-after 2000 ms
//runBlocking is done!
//runBlocking last line
//-no wait here it can execute while first child is waiting 4000ms
//child coroutine 2 is done
//after 4000 ms
//child coroutine 1 is finally done
//Line after run blocking wont execute until its complete!



//runBlocking example 6 - two runblocking at same level
fun main6(){
    runBlocking {
            delay(4000.milliseconds)
            println("runBlocking1 is finally done")
    }
    runBlocking {
        println("runBlocking2 is finally done")
    }
    println("Line after run blocking wont execute until its complete!")
}
//- both after 4000 ms
//runBlocking1 is finally done
//runBlocking2 is finally done

