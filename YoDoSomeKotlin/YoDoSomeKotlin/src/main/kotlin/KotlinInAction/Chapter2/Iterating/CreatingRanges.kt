package com.vav.KotlinInAction.Chapter2.Iterating

fun main(){
    println(isNotOddLessThan10(3))
}

//We can check if c exist in a range using "in" operator.
fun isLetter(ch: Char) = ch in 'A'..'Z' || ch in 'a'..'z'

//we can also use step and !in if we dont want
fun isNotOddLessThan10(i: Int) = i !in 1..11 step 2

fun checkVarious(c: Char) = when(c){
    in '1'..'9' -> "integer"
    in 'A'..'Z' -> "character"
    else -> "In don't know"
}