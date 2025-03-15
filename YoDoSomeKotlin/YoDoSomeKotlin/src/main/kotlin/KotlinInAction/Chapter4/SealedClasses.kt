package com.vav.KotlinInAction.Chapter4

sealed class Expression {
    data class Number(val value: Int): Expression()
    data class Add(val expression1: Expression, val expression2: Expression): Expression()
    data class Multiply(val expression1: Expression, val expression2: Expression): Expression()
    data class Subtract(val expression1: Expression, val expression2: Expression): Expression()
    data class Divide(val expression1: Expression, val expression2: Expression): Expression()

    fun evaluate(): Int{
        return when(this){
            is Number -> this.value
            is Add -> expression1.evaluate() + expression2.evaluate()
            is Multiply -> expression1.evaluate() * expression2.evaluate()
            is Subtract -> expression1.evaluate() - expression2.evaluate()
            is Divide -> {
                val expr2 = expression2.evaluate()
                if(expr2 != 0){
                    expression1.evaluate() / expression2.evaluate()
                }else{
                    throw IllegalArgumentException("Divide by zero!")
                }
            }
        }
    }
}

fun main(){
    val five = Expression.Number(5)
    val three = Expression.Number(3)

    val seven = Expression.Number(7)

    val fivePlusThree = Expression.Add(five, three)

    val fivePlusThreeMultiplySeven = Expression.Multiply(fivePlusThree, seven)



    println(fivePlusThreeMultiplySeven.evaluate())

}

