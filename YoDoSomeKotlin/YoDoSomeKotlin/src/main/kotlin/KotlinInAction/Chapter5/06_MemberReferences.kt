package com.vav.KotlinInAction.Chapter5

import com.vav.KotlinInAction.Chapter6.Person
import com.vav.KotlinInAction.Chapter6.personAge


/*
    Lambda allows us to pass a code as a function.
    But if the code itself is a function and nothing more?

    Then lambda can be replaced by something called member references.

    In below example the function maxBy accepts a lambda that takes input as a Person
    and returns an age. It does nothing more
    So we separate Person and age with a :: which is called member referencing.
    Person is the first parameter of lambda and then comes :: and member.

    Member references are of 2 types:
    1. Unbound member references
    2. Bound member references

    In case of Unbound member references our function that consumes lambda can have many params
    but it must have lambda as a param.
    The lambda itself can have many params but the first param should be of the same type as the
    class of the member reference.
    The member reference if it's a method must match the remaining params of the lambda in signature

    for example in below code:
    * the function fun stop() of the Vehicle matches the stop lambda params which is just class
    * The function fun drive(speed: Int) matches the drive lambda with params which is Vehicle, Int

    Receiver: The first parameter that is the object of the class itself is called Receiver.

    The reason these are called unbound member references is because they are not bound to an object yet
 */

fun main(){
    val max = personAge.maxBy(Person::age)

    compute(Oper::add)

    drive(false, Vehicle::drive)

    stop(Vehicle::stop)
}

class Vehicle{
    lateinit var name: String

    fun stop(){
        println("Stopping")
    }

    fun drive(speed: Int){
        println("driving")
    }
}

fun drive(drink: Boolean, lambda: (Vehicle, Int) -> Unit): Boolean{
    return true
}

fun stop(lambda: (Vehicle) -> Unit): Boolean{
    return true
}
