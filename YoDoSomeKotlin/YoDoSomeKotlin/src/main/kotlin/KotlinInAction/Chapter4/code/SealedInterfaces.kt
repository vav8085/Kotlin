package com.vav.KotlinInAction.Chapter4.code

interface Divisible{
    fun isDivisible(a: Int, b: Int): Boolean{
        return b != 0
    }
}

sealed interface Evaluate{
    data class Number(val n: Int): Evaluate {
        override fun eval(): Int {
            return n
        }
    }

    data class Addition(val a: Evaluate, val b: Evaluate): Evaluate {
        override fun eval(): Int {
            return a.eval() + b.eval()
        }
    }

    fun eval(): Int
}

class Division(val a: Evaluate, val b: Evaluate): Evaluate, Divisible {
    override fun eval(): Int {
        val left = a.eval()
        val right = b.eval()
        if (isDivisible(left,right))
            return left / right
        else throw IllegalArgumentException("divide by zero!")
    }
}

fun main(){
    val a = Evaluate.Number(5)
    val b = Evaluate.Number(6)
    val aPlusB = Evaluate.Addition(a, b)

    val c = Evaluate.Number(11)

    val aPlusBByC = Division(aPlusB, c)

    println(aPlusBByC.eval())
}