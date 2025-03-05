package com.vav.KotlinInAction.Chapter3

import javax.swing.JPopupMenu.Separator

/*
    In kotlin all collection interfaces are readonly by default

    that means list, set and map are all immutable

    we also have mutable interface counterparts
    mutablelist, mutableset, mutablemap
 */


fun main(){

    joinToString(listOf("apple", "pineapple", "banana"),";",",",",")
    /*
    KOTLIN TIP:
    In case of above implementation if we want to use this method with multiple string arguments then its
    easy to forget which parameter is which.
    This is a big issue when we have a method that takes multiple boolean flags. Its difficult to identify params
    One way is to check method signature or comment. Other is to use Enum types.

    IN CASE OF KOTLIN WE CAN HAVE NAMED ARGUMENTS WHICH HELP US EASILY SPECIFY WHAT VALUE IS BEING SENT
    IN INTELLIJ WE HAVE OPTION TO CHANGE SIGNATURE TO NAMED ARGUMENTS IN REFACTOR OPTION.
 */
    joinToString(collection = listOf("apple", "pineapple", "banana"), separator = ";", prefix = ",", postfix = ",")

    //avoided creating multiple overridden methods using default param values.
    joinToStringDefaultParams(collection = listOf("apple", "pineapple", "banana"), separator = ";")
    joinToStringDefaultParams(collection = listOf("apple", "pineapple", "banana"), separator = ";", prefix = "")
    joinToStringDefaultParams(collection = listOf("apple", "pineapple", "banana"))
}

/*
    Map can be initialized in multiple ways in kotlin
 */
fun initializeMap(){
    val map = mutableMapOf(1 to "one", 2 to "Two")
    val map1 = mutableMapOf(Pair(1, "one"), Pair(2, "two"))
}

/*
    Java collections have a toString method which returns the collection in a string format.
    The formatting if this is default and cannot be changed without writing a separate method to do it or use
    third party libraries like Guava or Apache commons.

    Kotlin has a method available for collections called joinToString()

    joinToString uses a StringBuilder inside it and append a prefix at the beginning and a postfix at the end
    The implementation looks something like below:

    public fun <T> Iterable<T>.joinToString(separator: CharSequence = ", ",
                                       prefix: CharSequence = "",
                                       postfix: CharSequence = "",
                                       limit: Int = -1,
                                       truncated: CharSequence = "...",
                                       transform: ((T) -> CharSequence)? = null): String {
    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()

}
 */

/*
    lets create our own joinToString
 */
fun <T> joinToString(collection: Collection<T>, separator: String, prefix: String, postfix: String): String{
    val result = StringBuilder()

    //remember this? ;)
    for((index, element) in collection.withIndex()){
        if(index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

/*
    In kotlin we also have default arguments. These can help avoid multiple method overload problem in java.
 */

fun <T> joinToStringDefaultParams(collection: Collection<T>, separator: String = ",", prefix: String = "", postfix: String = ""): String{
    val result = StringBuilder()

    //remember this? ;)
    for((index, element) in collection.withIndex()){
        if(index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

/*
    JAVA KOTLIN COMPATIBILITY for default arguments

    If you are using a kotlin function from java and want to use a method with default arguments then you can
    annotate the function with @JvmOverloads

    This automatically generates overloaded methods that can be used in java.
 */

@JvmOverloads
fun <T> joinToStringDefaultParamsJVM(collection: Collection<T>, separator: String = ",", prefix: String = "", postfix: String = ""): String{
    val result = StringBuilder()

    //remember this? ;)
    for((index, element) in collection.withIndex()){
        if(index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}
