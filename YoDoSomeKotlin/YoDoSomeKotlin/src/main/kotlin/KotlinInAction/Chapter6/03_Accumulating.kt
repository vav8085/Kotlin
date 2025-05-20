package com.vav.KotlinInAction.Chapter6

/*
    *   Accumulate values - reduce and fold methods are used to accumulate values in a collection.
                            They return a single value which is gradually built up in an accumulator by
                            going over entire collection.
        1.  reduce() -      Reduce starts with first value of the collection in an accumulator, our lambda
                            is then called on each following element one by one and result is calculated.
 */

fun main(){
    reduceFxSum()
    separator()
    reduceFxProduct()
    separator()
    reducePerson()
}


fun reduceFxSum(){
    println(listOfIntegers.reduce{acc, current -> current + acc})
}
//53

fun reduceFxProduct(){
    println(listOfIntegers.reduce{acc, current -> current * acc})
}
//5328

fun reducePerson(){
    println(personAge.reduce{acc, current -> Person("${acc.name} + ${current.name}", acc.age + current.age)})
}
//Person(name=Batman + Spiderman + Flash, age=76)


