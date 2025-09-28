package com.vav.KotlinInAction.Chapter5

val add = { a: Int, b: Int -> a + b }
val subtract = { a: Int, b: Int -> a - b }
val product = { a: Int, b: Int -> a * b }

fun compute(a: Int, b: Int, operation: (Int, Int) -> Int): Int{
    return operation.invoke(a,b)
}

fun compute(operation: (Oper) -> Int): Int{
    val oper = Oper()
    return operation.invoke(oper)
}

class Oper{
    fun add():Int{return 2}
}

fun main(){
    println(compute(2,3,add))
    println(compute(2,3,subtract))
    println(compute(2,3,product))
    println(add(1,2))
    println(subtract(2,3))
    println(product(5,2))
}

//5
//-1
//6