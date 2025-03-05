package com.vav.KotlinInAction.Chapter3

fun main(){
    println("hola".lastChar)

    val sb = StringBuilder()
        sb.append("hello").lastChar = 'o'
    println(sb.lastChar)
}

val String.lastChar: Char
    get() = this[this.length - 1]

/*
    Extension properties can be defined just like extension functions

    They dont have any initializers neither they have any backing fields.

    If its a val then you have to define a custom getter
    if its a var then you have to define both custom getter and setters.
 */

var StringBuilder.lastChar: Char
    get() = this.get(length - 1)
    set(value){
        this.setCharAt(length - 1, value)
    }