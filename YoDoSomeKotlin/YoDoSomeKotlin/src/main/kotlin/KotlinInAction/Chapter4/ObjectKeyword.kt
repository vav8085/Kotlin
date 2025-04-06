package com.vav.KotlinInAction.Chapter4

import java.util.Comparator

/*
    In kotlin Object keyword is used 3 ways
    1. To create a singleton
    object ClassName{}

    2. Companion object - To declare static members and functions
    companion object {}

    3. Object expressions - Used to create similar to anonymous objects in java
    object: Class(){}
 */

//Singleton
/*
    A singleton class and its object is created at the same time

    A singleton class cannot have a constructor

    The initial state of the object class is defined in its body
    because no constructor and its params.
 */

object Payroll{
    private val employees = mutableListOf("John Wick", "James Bond")

    fun calculateSalary(){
        for(employee in employees){
            println("Salary is:...")
        }
    }
}

/*
    Objects can also be created by inheriting from classes and interfaces

    Below is an object of a custom comparator

    This object can be passed to any function that uses a comparator

    These objects can also be declared inside a class. This will be
    different from companion object
 */

    object MyComparator: Comparator<Int>{
        override fun compare(o1: Int, o2: Int): Int {
            return o1.compareTo(o2)
        }
    }


fun main(){
    Payroll.calculateSalary()

    //use of comparator
    println(MyComparator.compare(1,2))

    val list = listOf(5,3,4,2,1)

    println(list.sortedWith(MyComparator))
    //this is similar to
    println(list.sortedWith({a,b -> a.compareTo(b)}))
    //which is similar to an anonymous function
    println(list.sortedWith(fun(a: Int, b: Int): Int {
        return a.compareTo(b)
    }))

    Student.printName()
}

/*
    Few different ways to create static functions in a class

    1. top level functions
    2. object in a class
    3. companion object in a class

    Top level functions cannot access private fields and methods of the class

    objects in the class can access its private properties and methods.

    Exactly 1 object in the class can be made a companion object.
    Any properties and methods in the companion object can be called directly using the
    class name

    companion object and properties cannot be called using object of a class. They are static
    and can only be called using class name.
 */

class Student{
    companion object{
        fun printName(){
            println("John")
            //Now this can be called as Student.printName()
        }
    }
}
