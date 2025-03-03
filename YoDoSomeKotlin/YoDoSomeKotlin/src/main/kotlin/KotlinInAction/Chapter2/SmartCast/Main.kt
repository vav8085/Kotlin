package com.vav.KotlinInAction.Chapter2.SmartCast

fun main(){
    //this will evaluate to: 5 + 4 + 6 * 3 = 27
    println(eval(Sum(Sum(Num(5), Num(4)), Product(Num(6), Num(3)))))
}

/*
    This is a vary good example of Polymorphism as well as a recursive function.

    We are first checking if e is Num
    Then we typecast it to a Num. Notice that this typecast isnt necessary like in Java
    In Kotlin compiler already knows the value of type so we can skip "val num =  e as Num"

    Notice that in the else if statement we are not checking the type
 */
fun eval(e: Expr): Int{
    if(e is Num){
        val num =  e as Num
        return num.value
    }else if(e is Sum){
        return eval(e.left) + eval(e.right)
    }else if (e is Product){
        return eval(e.left) * eval(e.right)
    } else throw IllegalArgumentException("Incorrect expression!")
}