package com.vav.KotlinInAction.Chapter2.code.SmartCast

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

    Also notice that there is a background color showing for e. This means this value is a smartcast
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

//a better eval using property of if expression

fun eval1(e: Expr): Int{
    return if(e is Num){
        e.value
    }else if(e is Sum){
        eval(e.left) + eval(e.right)
    }else if (e is Product){
        eval(e.left) * eval(e.right)
    } else throw IllegalArgumentException("Incorrect expression!")
}

//more concise

fun eval2(e: Expr): Int{
    return if(e is Num) e.value
    else if(e is Sum) eval(e.left) + eval(e.right)
    else if (e is Product) eval(e.left) * eval(e.right)
    else throw IllegalArgumentException("Incorrect expression!")
}

//using when. Notice that in this when we check is Num ->
//this is a special type of when where we can check the type of the argument
fun eval3(e: Expr): Int{
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        is Product -> eval(e.left) * eval(e.right)
        else -> throw IllegalArgumentException("Incorrect expression!")
    }
}

//the last statement in a block is the value that is returned
//this does not hold true for regular functions. regular functions have to have a return statement
fun eval4(e: Expr): Int{
    return if(e is Num){
        println(e.value)
        e.value
    }else if(e is Sum){
        println("${e.left}...${e.right}")
        eval(e.left) + eval(e.right)
    }else if (e is Product){
        println("${e.left}...${e.right}")
        eval(e.left) * eval(e.right)
    } else throw IllegalArgumentException("Incorrect expression!")
}
