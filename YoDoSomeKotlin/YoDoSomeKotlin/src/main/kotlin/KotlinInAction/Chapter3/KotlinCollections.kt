package com.vav.KotlinInAction.Chapter3

/*
    In kotlin all collection interfaces are readonly by default

    that means list, set and map are all immutable

    we also have mutable interface counterparts
    mutablelist, mutableset, mutablemap
 */


fun main(){

}

/*
    Map can be initialized in multiple ways in kotlin
 */
fun initializeMap(){
    val map = mutableMapOf(1 to "one", 2 to "Two")
    val map1 = mutableMapOf(Pair(1, "one"), Pair(2, "two"))
}



