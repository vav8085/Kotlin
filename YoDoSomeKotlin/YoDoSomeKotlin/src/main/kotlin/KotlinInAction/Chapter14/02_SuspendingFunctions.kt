package com.vav.KotlinInAction.Chapter14

import kotlin.concurrent.thread

fun main(){
    threadFun()
}

fun threadFun(){
    println("Thread ${Thread.currentThread().name}")
    thread {
        println("Now I am on ${Thread.currentThread().name}")
    }
}

/*
    Coroutines are lightweight abstractions over threads. We can create thousands of threads
    and millions tasks using coroutines
 */

