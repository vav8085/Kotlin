package com.vav.KotlinInAction.Chapter2.code.Iterating

fun main(){
    //iterateOverArrayWithIndex()
    iterateOverMap()
}

/*
* This can be very helpful for algo problems
*
* No need to create another variable for value
* */
fun iterateOverArrayWithIndex(){
    val array = intArrayOf(9,10,11,12,13,14,15)

    for ((index, value) in array.withIndex()){
        println("$index has value $value")
    }
}

/*
* Similarly we can iterate over maps
*
* in case of maps we can have a pair
* (key, value)
* */
fun iterateOverMap(){
    val map = hashMapOf(Pair(1,"one"),Pair(2,"two"),Pair(3,"three"),Pair(4,"four"),Pair(5,"five"))

    for ((number, string) in map){
        println("$number is called $string")
    }
}
