package com.vav.KotlinInAction.Chapter4.archive.code

/*

    In Kotlin all classes are final by default unlike java where classes were open for inheritance all the time.

    Inheritance can make things complicated. If child classes are inheriting from a class and parent in modified
    then children can behave unexpected ways.

    This is why a good practice is to either mark the class final or provide a detailed document about how to inherit it.

    In case of Kotlin all the classes, methods and properties are final by default and they have to marked open if they
    have to be inherited or overridden.

    If we override from an open class then all the overridden methods in it are by default open
    you have to explicitly mark them final in the superclass if you want to block inheritance

    Having a class final has advantage that it can be smart cast as long as the variable being cast hasn't changed after
    type check.
    This means the class property must be a val without a custom accessor. This is because a custom accessor can change the
    property. Also the property must be final because a subclass can create a custom accessor for it if its not.

    Because most properties are final by default they can be smart cast
 */

open class RichButton: Clickable {
    override val clicked: Boolean
        get() = TODO("Not yet implemented")

    override fun click() {
        TODO("Not yet implemented")
    }

    fun disable(){

    }
    open fun animate(){

    }
}
/*
    You can override animate and click because they were open in the superclass
 */
open class ThemedButton: RichButton() {
    override fun animate() {
        super.animate()
    }

    override fun click() {
        super.click()
    }
}
/*
    Animate and chick are by default open now
 */
class MyButton: ThemedButton(){
    override fun animate() {
        super.animate()
    }

    override fun click() {
        super.click()
    }
}

