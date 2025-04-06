package com.vav.KotlinInAction.Chapter5

/*
    A first class function is a function that can be treated as a value
    it can be passed in other functions and can be stored in a variable.
    it can be returned from a function.

    Lambdas are first class functions in Kotlin

    Immutability - variables that don't change once they have assigned values
    No side effects - a function does that takes input and returns output without
    modifying any outside variables.
 */
class FirstClassFunctions {
}

fun interface ClickListener{
    fun onClick(v: Int)
}

class Button{
    private var clickListeners = mutableListOf<ClickListener>()

    fun setOnClickListener(clickListener: ClickListener){
        clickListeners.add(clickListener)
    }
    fun click(){
        clickListeners.forEach {
            it.onClick(100)
        }
    }
}

fun main(){
    val button = Button()

    button.setOnClickListener(object :ClickListener{
        override fun onClick(v: Int) {
            println("button $v clicked")
        }
    })

    button.setOnClickListener { v -> println("button $v clicked") }

    button.click()
}
