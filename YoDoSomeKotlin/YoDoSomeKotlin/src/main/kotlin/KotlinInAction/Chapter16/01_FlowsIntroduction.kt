package com.vav.KotlinInAction.Chapter16

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds


/*
    *   Flows are abstraction over coroutines
    *   Allows working with multiple sequential values.
    *   Flows are inspired by reactive streams.
    *   Good for cases when some computation returns values over time
    *   In such cases we want to return these values asynchronously rather
        than wait for entire function to complete.
 */

fun main(){
    runBlocking {
        val list = createValues()
        for(item in list){
            println(item)
        }
    }
}

suspend fun createValues(): List<Int>{
    return buildList {
        add(1)
        delay(1000.milliseconds)
        add(2)
        delay(1000.milliseconds)
        add(3)
        delay(1000.milliseconds)
    }
}

//after 3 seconds all print together. the reason could be that
//they are added with delay but retrived at same time.
//1
//2
//3