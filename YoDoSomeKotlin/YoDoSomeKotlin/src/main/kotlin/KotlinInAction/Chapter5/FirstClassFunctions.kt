package com.vav.KotlinInAction.Chapter5

import com.vav.KotlinInAction.Chapter4.Person

/*
    A first class function is a function that can be treated as a value
    it can be passed in other functions and can be stored in a variable.
    it can be returned from a function.

    Lambdas are first class functions in Kotlin

    Immutability - variables that don't change once they have assigned values
    No side effects - a function does that takes input and returns output without
    modifying any outside variables.
 */
class FirstClassFunctions {
}

fun interface ClickListener{
    fun onClick(v: Int)
}

class Button{
    private var clickListeners = mutableListOf<ClickListener>()

    fun setOnClickListener(clickListener: ClickListener){
        clickListeners.add(clickListener)
    }
    fun click(buttonId: Int){
        clickListeners.forEach {
            it.onClick(buttonId)
        }
    }
}

/*
    Lambdas and collections

    In kotlin there are various lambda functions that can be passed to make
    trivial operations on collections easier for example
    id we want to find the maximum value in a collection we will loop over it
    and store the maximum value in a variable and compare every
    next item with it
 */

fun findMaximum(persons :List<Person>): Person{
    var maxPerson = persons[0]

    for(i in 1..persons.size - 1){
        if(persons[i].age > maxPerson.age) maxPerson = persons[i]
    }
    return maxPerson
}

/*
    But kotlin has easier way of finding maximum value using
    maxByOrNull

    The method takes a lambda / anonymous function used to calculate the
    maximum value. The function itself takes an element from collection
    and returns which field of this element  e.g. "age" can be used for
    comparison

    maxByOrNull is a Kotlin extension function that operates on collections.
    It iterates through the persons collection and compares the values returned by
    the provided selector function for each Person object.

    @SinceKotlin
public inline fun <T, R : Comparable<R>> Iterable<T>.maxByOrNull(
    selector: (T) -> R
): T

 return persons.maxByOrNull(fun(it: Person): Int {
        return it.age
    })!!
 */
fun findMaximumKotlin(persons: List<Person>): Person{
    return persons.maxByOrNull { it: Person -> it.age }!!
}

/*
    In above lambda we are just delegating to a single property age so lambda can
    be written as Person::age which means the same thing above
 */
fun findMaximumKotlinDelegate(persons: List<Person>): Person{
    return persons.maxByOrNull(Person::age)!!
}

fun main(){
    val button = Button()

    button.setOnClickListener(object :ClickListener{
        override fun onClick(v: Int) {
            println("button $v clicked")
        }
    })

    button.setOnClickListener { v -> println("button $v clicked") }

    button.click(100)

    // Lambdas and collections

    println(findMaximumKotlin(listOf(Person("john", 34), Person("Batman", 28))).name)

    println(findMaximumKotlinDelegate(listOf(Person("Ironman", 55), Person("Batman", 28))).name)


}
