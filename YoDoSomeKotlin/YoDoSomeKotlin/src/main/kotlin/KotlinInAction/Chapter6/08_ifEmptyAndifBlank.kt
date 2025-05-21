package com.vav.KotlinInAction.Chapter6

/*
    *   ifEmpty{}   -   provides a Lambda to provide a values to a mutable collection if its empty.
                        NOTICE THAT THE ORIGINAL COLLECTION REMAINS EMPTY.
    *   ifBlank{}   -   Similar to ifEmpty but for Strings

    NOTE: Remember that original collection does not change. These methods only provide a replacement or default values.
 */

fun main() {
    isEmptyFx()
    separator()
    isEmptyPerson()
    separator()
    isEmptyMap()
    separator()
    isBlankFx()
}

val emptyMutableList = mutableListOf<Int>()
val emptyMutablePersonAge = mutableListOf<Person>()
val emptyMap = mapOf<Int, String>()
val emptyString = ""

fun isEmptyFx() {
    emptyMutableList.ifEmpty { mutableListOf<Int>(1, 2, 3, 4, 5) }
    println(emptyMutableList)

    println(emptyMutableList.ifEmpty { mutableListOf<Int>(1, 2, 3, 4, 5) })
}

fun isEmptyPerson() {
    println(emptyMutablePersonAge.ifEmpty { listOf<Person>(Person("IronMan", 30), Person("WonderWoman", 26)) })
}

fun isEmptyMap() {
    println(emptyMap.ifEmpty { mapOf(Pair(1, "One"), Pair(2, "Two")) })
}

fun isBlankFx() {
    println(emptyString.ifBlank { "Hello World!" })
}
//[]
//[1, 2, 3, 4, 5]
//-----------------------------------------------------------
//[Person(name=IronMan, age=30), Person(name=WonderWoman, age=26)]
//-----------------------------------------------------------
//{1=One, 2=Two}
//-----------------------------------------------------------
//Hello World!