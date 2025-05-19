package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/*
    *   Coroutines can be cancelled by calling cancel() on their job
    *   Cancellation can be used if we dont need a coroutine anymore for example when we made a network call from
        a page and navigated away from that page we want to cancel any network calls made. Because we wont be using the
        response of these metwork calls. Leaving the network calls will waste computation time and memory occupied by
        any network response objects.
    *   We may also want to cancel a coroutine in case of any exception during computations.
    *   Another case if we are making some network calls sequentially using one response as request for another. In
        this case of one intermediate request fails then we would want to fail an entire or partial chain.
    *   All CoroutineBuilders return objects like Job and Deferred and provide methods like cancel() to cancel the coroutine.


    *   Always be careful about coroutine cancellation when acquiring resources. If a coroutine gets canceled when you have
        some resources like database connection or file stream open then they will get leaked.
        For this you can catch the CancellationException and close any resources and throw it again.

    *   Framework can also cancel a coroutine. For example, in Android we have viewModelScope which cancels any coroutines
        once ViewModel instance is cleared. 
 */


//Jobs got canceled after 200ms
fun main(){
    runBlocking {
        val job = launch {
            println("job 1 launched")
            delay(1000.milliseconds)
            println("job 1 complete")
        }
        val deferred = async {
            println("async 1 launched")
            delay(1000.milliseconds)
            println("async 1 complete")
        }
        delay(200.milliseconds)
        job.cancel()
        deferred.cancel()
    }
}
//job 1 launched
//async 1 launched