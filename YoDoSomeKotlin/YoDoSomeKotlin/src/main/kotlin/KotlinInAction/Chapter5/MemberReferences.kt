package com.vav.KotlinInAction.Chapter5

/*
    We can pass a lambda to a function

    This lambda could be calling a function (function B) itself.
    So we can pass this lambda to call this function B.
    But this is redundant.

    We can also pass a function to another function in kotlin.
    For this we can create a member reference for that function.

    Member references are basically a lambda but written in shorter syntax

    A member reference is a variable that can be passed to a function
    just like any other variable.

    A member reference can reference a single data member or a
    member function of a class

    Notice that ClassWithMembers::property is simply a lambda
    Input::Output
    {input: type -> input.output}
 */

class ClassWithMembers{
    var property = 10

    fun memberFunction(){
        println("hello I am a member of ClassWithMembers")
    }

    fun memberFunction2(greeting: String){
        println("$greeting I am a member of ClassWithMembers")
    }
}

val propertyRef = ClassWithMembers::property
//above is basically a lambda
val propertyRef2 = {classWithMembers: ClassWithMembers -> classWithMembers.property}

//function reference. you cant use it if class overloads functions with same name
//for e.g.
/*
    fun memberFunction(){
        println("hello I am a member of ClassWithMembers")
    }

    fun memberFunction(greeting: String){
        println("$greeting I am a member of ClassWithMembers")
    }
 */
val functionRef = ClassWithMembers::memberFunction
//this is same as above
val functionRef2 = {classWithMembers: ClassWithMembers -> classWithMembers.memberFunction()}

//Notice that you can still use this syntax even if the function has an input
val functionRef3 = ClassWithMembers::memberFunction2

val functionRef4 =
    { classWithMembers: ClassWithMembers, greeting: String -> classWithMembers.memberFunction2(greeting) }

fun main(){
    val classWithMembers = ClassWithMembers()
    println(propertyRef(classWithMembers))
    println(propertyRef2(classWithMembers))
    functionRef(classWithMembers)
    functionRef2(classWithMembers)

    //no input
    ::topLevelRef.invoke()
    //this is similar to
    //{ topLevelRef }.invoke()
}

//Now top level functions dont have a class
//so when invoking them we dont have to pass any input to a lambda
//so lambda looks like this { -> function}
//or {function}
//or ::function
val topLevelRef = println("hello")
