package com.vav.KotlinInAction.Chapter4

/*
    Data classes automatically implement methods like
    toString()
    hashcode()
    and equals()

    This is important to remember because normal class objects
    may not compare as same if above methods are not implemented
    So data classes are important
*/

class Person(val name: String, age: Int)

fun main(){
    val person1 = Person("Ram", 22)
    val person2 = Person("Ram", 22)

    println(person1 == person2)
    //false

    val person3 = PersonData("Ram", 22)
    val person4 = PersonData("Ram", 22)

    println(person3 == person4)
    //true
}

//Now lets do the same with data class

data class PersonData(val name: String, val age: Int)

