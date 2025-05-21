package com.vav.KotlinInAction.Chapter6

/*
    *   filter{}    -   As we have seen before filter can be used to filter the results from a collection
    *   filterNot{} -   filterNot does the reverse of filter and returns the elements that dont satisfy the condition.
    *   filter and filterNot can be used together to find both parts of the collection (satisfy / not satisfy)
    *   partition{} -   partition{} partitions the collection into 2 parts. one which satisfies the condition and other that
                        doesnt.
 */

fun main(){
    partitionFx()
    separator()
    partitionPerson()
}

fun partitionFx(){
    val (left, right) = listOfIntegers.partition { it > 5 }
    println(left)
    println(right)
}

fun partitionPerson(){
    val (left, right) = personAge.partition { it.name == "Batman" }
    println(left)
    println(right)
}

//[37, 6]
//[1, 2, 4, 3]
//-----------------------------------------------------------
//[Person(name=Batman, age=33)]
//[Person(name=Spiderman, age=22), Person(name=Flash, age=21)]

