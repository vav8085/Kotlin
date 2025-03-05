package com.vav.KotlinInAction.Chapter3


/*
    If you are using some java code or some kotlin code that cannot be extended then you can create extension functions
    This allows us to work on existing apis which we dont want to rewrite and still be able to add functions to them

    Extension functions can be called as a class member function but its defined outside that class.
 */
fun main(){
    println("Kotlin".lastChar())
}
/*
    An extension function on String class that allows to get its last character.
    This can also be used on an interfaces. just do Class/Interface.function()

    The class name in this case is called receiver type. Int this case its String class
    the receiver object is defined using "this" keyword. Receiver object can be used to access
    properties and methods of the Receiver type.

    extension function can be written on any jvm class. jvm classes are classes of jvm languages java, kotlin, groovy etc
    which are compiled to java classes.
 */

//fun Receiver type.function(): return_type = receiverObject.classMemberFunction(receiverObject.length - 1)
fun String.lastChar(): Char = this.get(this.length - 1)

