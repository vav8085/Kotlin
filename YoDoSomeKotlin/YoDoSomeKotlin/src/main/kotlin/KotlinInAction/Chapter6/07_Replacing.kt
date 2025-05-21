package com.vav.KotlinInAction.Chapter6

/*
    *   A functional programming style avoids mutable collections. In some cases we still want to work with mutable
        collections. Kotlin comes with functions that can help us change the collection.
    *   replaceAll{}    -   runs lambda on each value in the collection and replaces each element in the collection
                            with values computed by the lambda. replaceAll{} is available only for mutable collections.
                            **Notice that map{} function is different because map{} returns a new collection.
    *   fill{}  -   fill{} is similar to replaceAll but does not take a lambda. It takes a value and applies to each
                    value in a collection. Its good if we want to fill a collection with some default values.
 */

fun main() {
    replaceAllFx()
    separator()
    replaceAllPerson()
    separator()
    replaceAllMap()
    separator()
    replaceAllMap2()
}

val mutableListOfIntegers = listOfIntegers.toMutableList()
val mutablePersonAge = personAge.toMutableList()
val mutableFruitMap = fruitMap.toMutableMap()

fun replaceAllFx() {
    mutableListOfIntegers.replaceAll { it % 2 }
    println(mutableListOfIntegers)
}

fun replaceAllPerson() {
    mutablePersonAge.replaceAll { Person("Superman", 35) }
    println(mutablePersonAge)
}

//In case of map only value is replaced.
fun replaceAllMap() {
    mutableFruitMap.replaceAll { key, value -> value.uppercase() }
    println(mutableFruitMap)
}

fun replaceAllMap2() {
    mutableFruitMap.replaceAll { key, value ->
        if(key % 2 == 0) "Even fruit: $value" else "Odd fruit: $value"
    }
    println(mutableFruitMap)
}

//[1, 0, 1, 0, 0, 1]
//-----------------------------------------------------------
//[Person(name=Superman, age=35), Person(name=Superman, age=35), Person(name=Superman, age=35), Person(name=Superman, age=35)]
//-----------------------------------------------------------
//{1=APPLE, 2=ORANGE, 3=GRAPES, 4=KIWI}
//-----------------------------------------------------------
//{1=Odd fruit: APPLE, 2=Even fruit: ORANGE, 3=Odd fruit: GRAPES, 4=Even fruit: KIWI}

fun fillFx(){
    mutableListOfIntegers.fill(5)
    println(mutableListOfIntegers)
}

