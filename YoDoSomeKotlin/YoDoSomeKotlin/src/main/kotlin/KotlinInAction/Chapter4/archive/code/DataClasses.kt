package com.vav.KotlinInAction.Chapter4.archive.code

/*
    Data classes automatically implement methods like
    toString()
    hashcode()
    and equals()

    This is important to remember because normal class objects
    may not compare as same if above methods are not implemented
    So data classes are important
*/

class Person(val name: String, val age: Int)

fun main(){
    val person1 = Person("Ram", 22)
    val person2 = Person("Ram", 22)

    println(person1 == person2)
    //false

    val person3 = PersonData("Ram", 22)
    val person4 = PersonData("Ram", 22)

    println(person3 == person4)
    //true

    //Example of Copy() method
    val clive = PersonData("Clive", 28)
    println(clive.toString())
    val joshua = clive.copy(name = "Joshua", age = 16)
    println(joshua.toString())

    ///PersonData(name=Clive, age=28)
    //PersonData(name=Joshua, age=16)
}

//Now lets do the same with data class

data class PersonData(val name: String, val age: Int)

//data class properties can be both val and var. It's better to
//use val for immutability though. properties with var will also
//generate setters

data class PersonData1(var name: String, val age: Int)

/*
    On kotlin its a better practice to use the copy() method
    over making a data class fields as var. This keeps the
    original object immutable and creates a copy of the
    object.

    check main method for copy()
 */


