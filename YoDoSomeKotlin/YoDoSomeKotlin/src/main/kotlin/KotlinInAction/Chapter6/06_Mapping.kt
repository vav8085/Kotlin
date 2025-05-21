package com.vav.KotlinInAction.Chapter6

/*
    *   groupBy{}   -   Takes a lambda and on the bases of its result groups the collection into map.
                        Grouping happens based on some relation in the property of the collection

    *   associate{} -   Takes a lambda which is applied to each value in the collection. The return
                        must be of type Pair<key, value>. Both key and value can be calculated from the collection.

    *   Note that groupBy{} applies lambda to each collection value and groups them with computation results. Associate
        on the other hand always returns a Pair and values mapping to same key replace each other instead of getting grouped.
 */

fun main() {
    groupByFx()
    separator()
    groupByPerson()
    separator()
    associateFx()
}

fun groupByFx() {
    println(listOfIntegers.groupBy { it > 5 })
    println(listOfIntegers.groupBy { it > 5 }[true]) //Boolean -> list<Int>() map
    println(listOfIntegers.groupBy { it % 2 == 0 })
    println(listOfIntegers.groupBy { it % 2 })
}
//{false=[1, 2, 4, 3], true=[37, 6]}
//[37, 6]
//{false=[1, 37, 3], true=[2, 6, 4]}
//{1=[1, 37, 3], 0=[2, 6, 4]}

//robin and flash are same age so they are grouped together
fun groupByPerson() {
    println(personAge.groupBy { it.age > 22 }) //will make 2 groups boolean -> list<Person>
    println(personAge.groupBy { it.age }) // int -> list<person>
    println(personAge.groupBy { it.name.first() })
}
//{true=[Person(name=Batman, age=33)], false=[Person(name=Spiderman, age=22), Person(name=Flash, age=21), Person(name=Robin, age=21)]}
//{33=[Person(name=Batman, age=33)], 22=[Person(name=Spiderman, age=22)], 21=[Person(name=Flash, age=21), Person(name=Robin, age=21)]}
//{B=[Person(name=Batman, age=33)], S=[Person(name=Spiderman, age=22)], F=[Person(name=Flash, age=21)], R=[Person(name=Robin, age=21)]}

fun associateFx(){
    println(listOfIntegers.withIndex().associate { it.index to it.value })
    println(listOfIntegers.associate { ("Agent00$it") to it })
}
//{0=1, 1=2, 2=37, 3=6, 4=4, 5=3}
//{Agent001=1, Agent002=2, Agent0037=37, Agent006=6, Agent004=4, Agent003=3}

