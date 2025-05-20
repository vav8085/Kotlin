package com.vav.KotlinInAction.Chapter6

/*
    *   Accumulate values - reduce and fold methods are used to accumulate values in a collection.
                            They return a single value which is gradually built up in an accumulator by
                            going over entire collection.

        1.  reduce() -      Reduce starts with first value of the collection in an accumulator, our lambda
                            is then called on each following element one by one and result is calculated.

        2.  fold()   -      Unlike Reduce which always start accumulating from first value in the collection, Fold
                            allows us to provide a starting value for accumulator and builds over that. The starting
                            value may not be a value in the collection. Starting value is provided in the fold parameters.
                            * The type of the initial value and the return values should be same.
                            * The type of initial value and collection can be different.

        3.  runningReduce() -   Same as reduce but returns a list of accumulated values at each step instead of just the
                                final accumulation. This is good for operations like taking prefix Sum of an array.

 */

fun main() {
    reduceFxSum()
    separator()
    reduceFxProduct()
    separator()
    reducePersontoPerson()
    separator()
    foldFxSum()
    separator()
    foldFxProduct()
    separator()
    foldPersonToPerson()
    separator()
    foldPersonToName()
    separator()
    runningReduceFx()
    separator()
    runningFoldFx()
}


fun reduceFxSum() {
    println(listOfIntegers.reduce { acc, current -> current + acc })
}
//53

fun reduceFxProduct() {
    println(listOfIntegers.reduce { acc, current -> current * acc })
}
//5328

fun reducePersontoPerson() {
    println(personAge.reduce { acc, current -> Person("${acc.name} + ${current.name}", acc.age + current.age) })
}
//Person(name=Batman + Spiderman + Flash, age=76)

//NOT POSSIBLE
//fun reducePersonToName(){
//    println(personAge.reduce { acc, current -> acc.name + current.name })
//}

fun foldFxSum() {
    println(listOfIntegers.fold(25) { initial, current -> initial + current })
}
//78

fun foldFxProduct() {
    println(listOfIntegers.fold(10) { initial, current -> initial * current })
}
//53280

fun foldPersonToPerson() {
    println(
        personAge.fold(
            Person(
                "Sum of ages of:",
                0
            )
        ) { initial, current -> Person("${initial.name} + ${current.name}", initial.age + current.age) })
}
//Person(name=Sum of ages of: + Batman + Spiderman + Flash, age=76)

fun foldPersonToName(){
    println(
        personAge.fold(
            "Avengers:"
        ) { initial, current -> "$initial ${current.name}" })
}
//Avengers: Batman Spiderman Flash

fun runningReduceFx() {
    println(listOf(1,2,3,4,5).runningReduce { acc, current ->  acc + current})
}
//[1, 3, 6, 10, 15]

fun runningFoldFx() {
    println(listOf(1,2,3,4,5).runningFold(10) { acc, current ->  acc + current})
}
//[10, 11, 13, 16, 20, 25]