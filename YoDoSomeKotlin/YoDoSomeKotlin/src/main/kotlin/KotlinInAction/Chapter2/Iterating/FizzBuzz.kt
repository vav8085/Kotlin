package com.vav.KotlinInAction.Chapter2.Iterating

fun main(){
    //fizzBuzz()
    IteratingWithStep2()
}

/*
    In kotlin range is defined using ..
    e.g.
    1..10 is a range from 1 to 10 inclusive.

    a range can be stored in a variable

    val oneToTen == 1..10


    Lets understand integer ranges in Kotlin using a game called FizzBuzz

    If a number is divisible by 5 then we return Buzz
    If a number is divisible by 3 then we return Fizz
    If a number is divisible by both then we return FizzBuzz

 */
fun fizzBuzz(){
    for(i in 1..50){
        if(i % 15 == 0) println("FizzBuzz")
        else if (i % 5 == 0) println("Buzz")
        else if (i % 3 == 0) println("Fizz")
        else println("$i")
    }
}

/*
    In kotlin we can also iterate over a range with steps
    The steps tell what should be added / subtracted from start of the range to reach next value

    Below function prints an even range
    0,2,4,6,8,10
 */
fun IteratingWithStep(){
    for (i in 0..10 step 2){
        println("$i")
    }
}

/*
    What if we start with an odd number?

    then output will be all odd numbers
    9,7,5,3,1
    Notice that although we are reaching 0 but it falls between steps -1 and 1 so we will skip that
 */

fun IteratingWithStep2(){
    for (i in 9 downTo 0 step 2){
        println("$i")
    }
}
