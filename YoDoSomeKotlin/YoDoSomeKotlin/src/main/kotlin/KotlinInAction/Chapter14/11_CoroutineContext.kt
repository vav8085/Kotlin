package com.vav.KotlinInAction.Chapter14

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
    *   If we look at the parameters of launch() then its actually CoroutineContext param where we pass
        a dispatcher to it.
    *   CoroutineContext has many elements and dispatcher is one of those.
    *   Other elements that it contains are Job, CoroutineName, CoroutineExceptionHandler.
    *   We can override a CoroutineContext by passing other parameters to a coroutineBuilder using +
    *   In short coroutine context is like a container holding various properties of a coroutine.
    *   Passing params to coroutine can help us debug it and manage its lifecycle.
 */

fun main(){
    runBlocking(Dispatchers.IO + CoroutineName("Hello")) {

    }
}


