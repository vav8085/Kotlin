package com.vav.KotlinInAction.Chapter6

/*
    *   We have various predicates like all(), any(), none(), count() and find() that can be applied to collections.
    *   These methods take a lambda. They tell you true or false if some value exist in collection.
    *   none can be replaced with !any. similarly there is !all which means any.
    *   Using right predicate is the key but they can also be used other ways.
 */

fun main() {
    allFx()
    separator()
    allPerson()
    separator()
    allMap()
    separator()
    anyFx()
    separator()
    anyPerson()
    separator()
    anyMap()
    separator()
    noneFx()
    separator()
    nonePerson()
    separator()
    noneMap()
    separator()
    countFx()
    separator()
    countPerson()
    separator()
    countMap()
    separator()
    findFx()
    separator()
    findPerson()
    separator()
    findMap()
}

val canBeInClub27 = { p: Person -> p.age <= 27 }

val fruitMap = mapOf(Pair(1, "Apple"), Pair(2, "Orange"), Pair(3, "Grapes"), Pair(4, "Kiwi"))

fun allFx() {
    println("allFx")
    println(listOfIntegers.all { it > -1 })
}

fun allPerson() {
    println("allPerson")
    println(personAge.all(canBeInClub27))
}

fun allMap() {
    println("allMap")
    println(fruitMap.all { entry -> entry.value == "Orange" })
}

fun anyFx() {
    println("anyFx")
    println(listOfIntegers.any { value -> value > 5 })
}

fun anyPerson() {
    println("anyPerson")
    println(personAge.any(canBeInClub27))
}

fun anyMap() {
    println("anyMap")
    println(fruitMap.any { entry -> entry.value == "Orange" })
}

fun noneFx() {
    println("noneFx")
    println(listOfIntegers.none { it == 5 })
}

fun nonePerson() {
    println("nonePerson")
    println(personAge.none { it.name == "Batman" })
}

fun noneMap() {
    println("noneMap")
    println(fruitMap.none { it.value == "Apple" })
}

fun countFx() {
    println("countFx")
    println(listOfIntegers.count { it % 2 == 0 })
}

fun countPerson() {
    println("countPerson")
    println(personAge.count(canBeInClub27))
}

fun countMap() {
    println("countMap")
    println(fruitMap.count { it.value.startsWith('A') })
}

fun findFx() {
    println("findFx")
    println(listOfIntegers.find { it % 2 == 0 })
    println(listOfIntegers.find { it == 3 })
}

fun findPerson() {
    println("findPerson")
    println(personAge.find(canBeInClub27))
}

fun findMap() {
    println("cant find in map")

}

//allFx
//true
//-----------------------------------------------------------
//allPerson
//false
//-----------------------------------------------------------
//allMap
//false
//-----------------------------------------------------------
//anyFx
//true
//-----------------------------------------------------------
//anyPerson
//true
//-----------------------------------------------------------
//anyMap
//true
//-----------------------------------------------------------
//noneFx
//true
//-----------------------------------------------------------
//nonePerson
//false
//-----------------------------------------------------------
//noneMap
//false
//-----------------------------------------------------------
//countFx
//3
//-----------------------------------------------------------
//countPerson
//2
//-----------------------------------------------------------
//countMap
//1
//-----------------------------------------------------------
//findFx
//2
//3
//-----------------------------------------------------------
//findPerson
//Person(name=Spiderman, age=22)
//-----------------------------------------------------------
//cant find in map