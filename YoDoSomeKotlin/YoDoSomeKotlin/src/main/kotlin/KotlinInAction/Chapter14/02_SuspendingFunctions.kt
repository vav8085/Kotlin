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
    but millions of tasks using coroutines.
    *   Coroutines are cheap.
    *   It Can be created for both long and short-lived tasks.
    *   Can suspend without blocking system resources and resume later from where they left off.
    *   Coroutines bring a structure and hierarchy to your tasks using Structured Concurrency.
    *   Structured concurrency allows coroutine cancellation. So if current computation fails, then
        all the children coroutines automatically cancel.
    *   Coroutines code is sequential like normal code unlike reactive streams and threads.
    *   So basically, the coroutine itself gets suspended without blocking thread and other operations
        using the current thread can keep happening.
 */

//Suppose below two functions wait for data from network then if they run on UI thread then they will block it
//until data is received from the network.
fun login(credentials: String){}
fun loadUserData(userId: Int){}
//displays downloaded data
fun showData(data: String){}

fun showUserInfo(){
    login("John")
    loadUserData(7)
    showData("hello")
}

//----Login()__________Blocked thread_________loadUserData()______blocked thread________showData()

//But below two functions will simply get suspended without blocking the UI thread. Once the data is received,
//they will continue to execute normally.
suspend fun login1(credentials: String){}
suspend fun loadUserData1(userId: Int){}
//displays downloaded data
fun showData1(data: String){}

//----Login()__________thread not blocked coroutine suspended_________loadUserData()______thread not blocked coroutine suspended________showData()





