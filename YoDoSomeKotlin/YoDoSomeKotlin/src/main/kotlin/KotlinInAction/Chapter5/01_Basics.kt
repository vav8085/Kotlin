package com.vav.KotlinInAction.Chapter5

/*
    Traditional way we would create a click listener like below

    But amazing thing about lambdas are that you dont need to create an interface
 */

class Button {
    private lateinit var onClickListener: OnClickListener
    private lateinit var onClickLambda: () -> Unit
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun setOnClickListener(onClickListener: () -> Unit){
        onClickLambda = onClickListener
    }

    fun clickButton() {
        onClickListener.onClick()
        onClickLambda.invoke()
    }
}

interface OnClickListener {
    fun onClick()
}

fun main() {
    val button1 = Button()
    button1.setOnClickListener(object : OnClickListener {
        override fun onClick() {
            println("button 1 clicked!")
        }
    })

    button1.setOnClickListener {
        println("button 1 clicked using lambda!")
    }

    button1.clickButton()
}