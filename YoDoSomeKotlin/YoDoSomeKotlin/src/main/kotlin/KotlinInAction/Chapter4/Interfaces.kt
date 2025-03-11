package com.vav.KotlinInAction.Chapter4

/*
* You can define an interface in kotlin.
* Rules are very similar to Java but interfaces can have both methods and properties
* A class can implement as many interfaces
* you can also have properties in an interface but they cannot be initialized.
* The properties also have to be implemented
* */

interface Clickable{
    val clicked: Boolean
    fun click()
}

class Button: Clickable{
    private var _clicked = false

    override val clicked: Boolean
        get() = _clicked

    override fun click() {
        _clicked = true
        println("button clicked!")
    }
}

fun main(){
    Button().click()
}


/*
    In kotlin we can also provide a default implementation to methods. This default implementation comes handy when
    we dont want to modify every class that implement this interface when a new method is added to this interface
 */

interface SideScrollable{
    fun onScroll() = "I am scrolling left and right!"
}

class SideScrollView: SideScrollable{
    //this class does not need to implement SideScrollable class's onScroll method because its already having a default
    //implementation
}


/*
    Now what happens when we have 2 interfaces with the same methods with default implementation and we try to implement them?

    In this case how will we proceed if we want to implement both the methods?

    We have to provide a custom implementation in this case.

    We will override override fun onTouch() and in its body we can call
    parent's implementations.
 */

/*
    Can we do this with abstract/without default implemented methods in interface?

    No we cant because abstract methods dont have a default implementation so we cant call them.
 */

interface Scrollable{
    fun onTouch() = println("Scroll!")
}

interface Touchable{
    fun onTouch() = println("Touch!")
}

class View: Scrollable, Touchable{
    override fun onTouch(){
        //can call both or one or none
        super<Scrollable>.onTouch()
        super<Touchable>.onTouch()
    }
}

