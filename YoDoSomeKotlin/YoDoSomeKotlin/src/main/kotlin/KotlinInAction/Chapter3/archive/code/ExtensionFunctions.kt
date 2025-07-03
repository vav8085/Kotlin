package com.vav.KotlinInAction.Chapter3.archive.code


/*
    If you are using some java code or some kotlin code that cannot be extended then you can create extension functions
    This allows us to work on existing apis which we dont want to rewrite and still be able to add functions to them

    Extension functions can be called as a class member function but its defined outside that class.
 */
fun main(){
    println("Kotlin".lastChar())

    println(listOf("hello", "how", "are", "you?").joinToStringDefaultParams(" "))
    //prints "hello how are you?"
}
/*
    An extension function on String class that allows to get its last character.
    This can also be used on an interfaces. just do Class/Interface.function()

    Extension functions are static functions under the hood.

    The class name in this case is called receiver type. Int this case its String class
    the receiver object is defined using "this" keyword. Receiver object can be used to access
    properties and methods of the Receiver type.

    extension function can be written on any jvm class. jvm classes are classes of jvm languages java, kotlin, groovy etc
    which are compiled to java classes.

    Extension functions dont have access to private fields and behaviors.

    Extension functions have to be imported like all classes and functions
    Extension functions are imported just like other functions of a class.

 */

//fun Receiver type.function(): return_type = receiverObject.classMemberFunction(receiverObject.length - 1)
fun String.lastChar(): Char = this.get(this.length - 1)


//Lets write joinToString as extension function of a Collection interface

fun <T> Collection<T>.joinToStringDefaultParams(separator: String = ",", prefix: String = "", postfix: String = ""): String{
    val result = StringBuilder()

    //Notice that we remove collection parameter and we are using this instead of it. rest all is same
    for((index, element) in this.withIndex()){
        if(index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

/*
    Limitations of Extension function:
    1. Extension functions can work on a specific receiver type so sometimes its good to write an extension function
    over an implementation vs parent. For e.g. an extension function could work on a specific collection type
    string. It may not work on other types like integer and chars but all of these can be Collection

    e.g. our method joinToString may not work on a list of integers.

    2. Extension functions cannot be overridden.
    In kotlin you can make a class overridable using an open keyword before the class and its overridable methods.
    A class View can be extended by a class button. If we store a button object on a view reference then if we call
    view.onClick then it will call onClick of the view class. but if onClick is overridden in the button class then
    it will call onClick of the button class.
    val button = button()
    val view: View = button
    view.onClick()

    In case of extension functions this is not possible. Extension function of parent and child classes are different
    because they are defined outside the parent and child classes.

    The extension function can be in both parent and child class with same name. but because its static its determined
    at the compile time and not the runtime. This is unlike method overriding which is determined during runtime.

    3. if a class has a member function with same signature with extension function then it will be called instead.


 */
