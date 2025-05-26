package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

/*
    *   A normal cold flow using flow{} emit{} and collect{} is sequential.
    *   Values are sent one by one using a single coroutine.
    *   Sometimes we can have values where order doesnt matter and we can emit them in parallel.
    *   In such cases operation time can be reduce if they are done in parallel and normal cold observable will
        be less performant.

    *   Concurrent flows - These are flows that can use multiple coroutines to perform an operation in parallel.
                           we use channelFlow{} to create such flow.
 */

fun main() {
    runBlocking {
        concurrentFlow().collect{
            println(it)
        }
    }
}

fun sequentialFlow() = flow {
    repeat (10) {
        emit(Random.nextInt())
    }
}
//sequential calculation
//1567639829
//-310992304
//1792327425
//1280106804
//1484106243
//-613583205
//1373019542
//570813792
//760004515
//99020005

fun concurrentFlow() = channelFlow<Int> {
    repeat (10) {
        send(Random.nextInt())
    }
}

//concurrent calculation
//2033597931
//254354264
//1992122254
//655334261
//646940940
//1393679539
//-1253289224
//1639185973
//2119396695
//927408833