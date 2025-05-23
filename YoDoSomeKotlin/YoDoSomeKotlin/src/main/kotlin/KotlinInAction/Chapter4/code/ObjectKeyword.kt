package com.vav.KotlinInAction.Chapter4.code

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
    println(MyComparator.compare(1, 2))

    val list = listOf(5,3,4,2,1)

    println(list.sortedWith(MyComparator))
    //this is similar to
    println(list.sortedWith({a,b -> a.compareTo(b)}))
    //which is similar to an anonymous function
    println(list.sortedWith(fun(a: Int, b: Int): Int {
        return a.compareTo(b)
    }))

    Student.printName()

    //we are passing listener to Button2
    val button2 = Button2(listenerImpl)

    //we can also do this following way
    val button22 = Button2(object : TouchListener {
        override fun onHover() {
            println("button22 hovered")
        }

        override fun onTouch() {
            println("button22 touched")
        }
    })

    val dragView = DragView(object: DragListener {
        override fun onDrag() {
            TODO("Not yet implemented")
        }
    })

    val dragView2 = DragView({ println("hello") })

    val dragView4 = DragView2{ string ->
        println(string)
        string + "hello"
    }

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

/*
    Companion objects can also be used to create factory methods.
    i.e. static methods that can be used to create objects of a class.
 */

class Employee{
    constructor(name: String){

    }
    constructor(employeeNo: Int){

    }
}

//the other way to do above is by making primary constructor private
//and then use a companion object to create its instance
//factory methods can be used to control creation of objects
//for example you can add a condition that after employeeNo reaches 900 you stop creating new objects

class Employee1 private constructor(name: String){
    companion object{
        fun getInstanceWithName(name: String){
            Employee1(name)
        }
        fun getInstanceWithNameAndDepartment(employeeNo: Int){
            Employee1(employeeNo.toString())
        }
    }
}

// Companion objects can also have name just like other objects
//but usually because we can use them with class name, we dont name them

class CompanionObjectWithName constructor(){
    companion object Companion{
        //do something
    }
}


/*
    Anonymous inner classes using Object expressions

    Usually in kotlin we dont have interfaces with multiple
    methods. We usually have an interface with just 1 method
    because that can be converted to a lambda
 */

interface TouchListener{
    fun onTouch()
    fun onHover()
}

val listenerImpl = object : TouchListener {
    override fun onTouch() {
        println("View touched")
    }

    override fun onHover() {
        println("View hovered")
    }
}

class Button2(listener: TouchListener){
    init {
        listener.onTouch()
        listener.onHover()
    }
}
//Now go to Main method above


//Below is how we can have a functional interface created with only 1 function
fun interface DragListener{
    fun onDrag()
}

fun interface DragListener2{
    fun onDrag(string: String): String
}

class DragView(listener: DragListener){
}

class DragView2(listener: DragListener2){
}
//Now go to main
