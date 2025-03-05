package com.vav.KotlinInAction.Chapter3

/*

    In java we create many util classes with a lot of static methods
    In Kotlin we dont have to do this. We can just put the methods in a .kt file and use from there.
    No Util classes needed.

    These methods written directly in .kt files are called TOP LEVEL FUNCTIONS

    Notice that we cannot have more than one method with same name in the same package if we do this.

    In case of java we can call these methods using the filename as class name

    TopLevelFunctions.joinToString3(...)


    Similarly we can have TOP LEVEL PROPERTIES
    joinedString is a top level property.

    Top level properties are static and can also be used to create constants.

    const val UNIX_LINE_SEPARATOR = "\n"

    This is similar to writing in java
    public static final String UNIX_LINE_SEPARATOR = "\n"

    ****** KOTLIN MATH PACKAGE HAS TOP LEVEL METHOD AND PROPERTIES
    maxOf(), minOf()...

 */

var joinedString = ""
const val UNIX_LINE_SEPARATOR = "\n"

fun <T> joinToString3(collection: Collection<T>, separator: String, prefix: String, postfix: String): String{
    val result = StringBuilder()

    //remember this? ;)
    for((index, element) in collection.withIndex()){
        if(index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}