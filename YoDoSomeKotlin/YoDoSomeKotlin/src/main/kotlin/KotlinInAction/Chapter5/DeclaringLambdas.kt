package com.vav.KotlinInAction.Chapter5

import com.vav.KotlinInAction.Chapter4.Person

/*
    A lambda can be declared using a syntax { input -> output }
 */

val add = {a: Int, b: Int -> a + b}

fun main(){
    println(add(2,3))
    //returns 5
    lambdaWithoutInput()
    println("kotlin run ${kotlinRun(2,3)}")
    lambdaAsLastParam()
}

fun lambdaWithoutInput(){
    //lambda can also be without an input, below lambda just has a body and an output
    //such lambda does not make much sense though it can simply be written as println(42)
    { println(42) }.invoke()
}

/*
    A better way to call a block of code in kotlin is using run function
    Run can also be used to use expression syntax for a function with multiline
    body
    variables in run have their own scope
    last statement is returned
    can be used just like ?.let{} but doesnt have an 'it'
 */
fun kotlinRun(a: Int, b: Int): Int = run {
    a + b
}

/*
    Lets take an example of maxOrNull and see how many ways we can write it
    everything below means the same
 */

fun maxOrNullExample(){
    val persons = listOf( Person("john", 34), Person("Batman", 28))

    val maxPerson = persons.maxByOrNull({person: Person ->  person.age})
    val maxPerson2 = persons.maxByOrNull({person -> person.age})
    //when lambda doesn't have any input
    val maxPerson3 = persons.maxByOrNull({it.age})
    //when lambda is the only argument
    val maxPerson4 = persons.maxByOrNull{it.age}
    //when lambda simply delegates to a property Input::output
    val maxPerson5 = persons.maxByOrNull(Person::age)
}

fun lambdaAsLastParam(){
    val people = listOf( Person("john", 34), Person("Batman", 28))

    //the lambda is for transformation parameter in join to string
    //because this is the last parameter, it can be moved outside like below
    println(people.joinToString { it.name })
    //john, Batman
}

/*
    Accessing variable in scope

    A lambda can access variables in surrounding scope
    This can be local scope of surrounding method or all the way
    up to class members

    External variables that are accesses through the lambda are
    said to be captured by the lambda

    Notice that in java anonymous functions we can access
    the class members but can access only the
    final local variables of the enclosing functions
 */

//lambda captures prefix
//it can similarly capture class members
fun addPrefixToName(names: List<String>, prefix: String){
    names.forEach {
        println("$prefix$it")
    }
}