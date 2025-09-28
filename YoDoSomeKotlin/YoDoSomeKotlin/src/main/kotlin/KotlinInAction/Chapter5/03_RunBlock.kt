package com.vav.KotlinInAction.Chapter5

/*
    Run block is especially useful for executing multiple statements
    this could be computing some value or performing some collective set of operations
 */
fun main(){
    val sum = run{
        println(subtract(1,2))
        println(product(1,2))
        add(1,2)
    }
    println(sum)
}