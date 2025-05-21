package com.vav.KotlinInAction.Chapter6


/*
    *   maxByOrNull() - Returns the maximum value in the collection
    *   filterIndexed() - Used to filter but also receive index in lambda
    *   mapIndexed() - map but also receive indx in lambda
 */


fun main() {
    maxByOrNullEx()
    separator()
    maxByOrNullPerson()
    separator()
    filterIndexedFx()
    separator()
    filterIndexedPerson()
    separator()
    mapIndexedFx()
    separator()
    mapIndexedPerson()
}
val listOfIntegers = listOf(1, 2, 37, 6, 4, 3)

fun maxByOrNullEx() {
    val listOfString = listOf<String>("hello", "how", "are", "you")
    println(listOfIntegers.maxByOrNull { it }) //has a condition
    println(listOfIntegers.maxOrNull()) //just returns largest
    println(listOfString.maxOrNull())
}
//37
//37
//you
val personAge = listOf(Person("Batman", 33), Person("Spiderman", 22), Person("Flash", 21),Person("Robin", 21))

fun maxByOrNullPerson() {
    println(personAge.maxByOrNull { it.age })
    val oldestPerson = personAge.maxByOrNull { it.age }?.age
    println(personAge.filter { it.age == oldestPerson }.firstOrNull()?.name)
}

//Person(name=Batman, age=33)
//Batman

fun filterIndexedFx() {
    println(listOfIntegers.filterIndexed{index, element -> index % 2 == 0 && element > 3})
}
//[37, 4]

fun filterIndexedPerson() {
    println(personAge.filterIndexed { index, person ->  index > 1 && person.age > 19})
}
//[Person(name=Flash, age=21)]

fun mapIndexedFx() {
    println(listOfIntegers.mapIndexed { index, element ->  index + element})
}
//[1, 3, 39, 9, 8, 8]

fun mapIndexedPerson() {
    println(personAge.mapIndexed { index, person ->  Pair(index, person.name) })
}
//[(0, Batman), (1, Spiderman), (2, Flash)]

