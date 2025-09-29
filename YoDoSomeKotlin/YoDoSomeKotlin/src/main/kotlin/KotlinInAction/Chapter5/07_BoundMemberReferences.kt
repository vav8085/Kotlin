package com.vav.KotlinInAction.Chapter5

/*

    Bound member references are bound to an object

    You can use bounded member references to replace a lambda that does not take any parameter

    usually you will call the method using . compute1 { operation.add() }

    it just converts to compute1 (operation::add)
 */

fun main(){
    val operation = Operation()
    compute1 { operation.add() }
    compute1 (operation::add)
}

fun compute1(lambda: () -> Unit){

}

class Operation{
    fun add(){

    }
}