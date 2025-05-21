package com.vav.KotlinInAction.Chapter6


/*
    *   Filter function is used to filter some collection and get the values that can pass through the filter condition.
    *   Filter does not transform the collection it only extracts the values from the collection.

    *   Map allows you to transform the collection and return the output of transformation. Map applies the lambda
        to each value in collection.
 */


data class Person(val name: String, val age: Int)

fun main(){
    filterEx()
    separator()
    filterPeople()
    separator()
    mapEx()
    separator()
    mapPeople()
    separator()
    filterMapChain()
}

fun separator(){
    println("-----------------------------------------------------------")
}

fun filterEx(){
    val list = listOf(1,2,3,4,5)
    println(list.filter { it > 3 })
}
//[4, 5]

fun filterPeople(){
    val people = listOf(Person("John", 30), Person("Jason", 25), Person("Bond", 35))
    println(people.filter { it.age > 30 }.toString())
}
//[Person(name=Bond, age=35)]

fun mapEx(){
    val list = listOf(1,2,3,4,5,6,7)
    println(list.map { it * it })
}
//[1, 4, 9, 16, 25, 36, 49]

fun mapPeople(){
    val people = listOf(Person("John", 30), Person("Jason", 25), Person("Bond", 35))
    println(people.map { Pair(it.name, it.age) })
    println(people.map { it.name })
}
//[(John, 30), (Jason, 25), (Bond, 35)]
//[John, Jason, Bond]

fun filterMapChain(){
    val people = listOf(Person("John", 30), Person("Jason", 25), Person("Bond", 35))
    println(people.filter { it.age > 30 }.map { it.name })
}
//[Bond]