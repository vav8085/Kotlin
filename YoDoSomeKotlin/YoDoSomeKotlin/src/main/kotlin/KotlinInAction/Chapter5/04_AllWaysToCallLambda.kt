package com.vav.KotlinInAction.Chapter5

data class Person(val name: String, val age: Int)

fun main(){
    val list = listOf<Person>(Person("Alice", 29), Person("Bob", 31))

    println(list.maxBy { p: Person -> p.age })
    println(list.maxBy { p -> p.age })
    println(list.maxBy { it.age })
    println(list.maxBy(Person::age))
}