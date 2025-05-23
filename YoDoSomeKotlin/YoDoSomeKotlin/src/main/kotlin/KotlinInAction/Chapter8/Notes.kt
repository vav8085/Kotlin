package com.vav.KotlinInAction.Chapter8

/*
    *   You have unsigned types like UInt, UShort, ULong and UByte
    *   No automatic conversion of types in Kotlin. Even when you assign to a larger type.
        val i = 1
        val j: Long = i // wont work.
        val k = i.toLong()
        val l = 5L //will be long
        val d = 5.0
        val f = 5.0F

    *   Different types also cannot be compared. You have to convert first before comparing.
    *   Arithmetic operators can work on different types:
        val b: Byte = 1
        val k = b + 10L //will work
    *   Overflow and underflow in kotlin work same as java
        Int.MAX_VALUE + 1 will wrap around to negative just like java
    *
 */


/*
    *   String to int and byte can be done using toInt() and toByte functions
    *   toInt() and toByte() etc throw NumberFormatException when conversion cannot be performed.
    *   A better option is to use
 */

fun main(){
    println(stringToInteger("500") + 10)
    try {
        println(stringToInteger("hello") + 10) //this will throw NumberFormatException
    }catch (e: NumberFormatException){
        println(stringToIntegerOrNull("hello"))
        e.printStackTrace()
    }
}

fun stringToInteger(s: String): Int{
    return s.toInt()
}


fun stringToIntegerOrNull(s: String): Int?{
    return s.toIntOrNull()
}

//510
//null
//java.lang.NumberFormatException: For input string: "hello"
//	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
//	at java.base/java.lang.Integer.parseInt(Integer.java:588)
//	at java.base/java.lang.Integer.parseInt(Integer.java:685)
//	at com.vav.KotlinInAction.Chapter8.NotesKt.stringToInteger(Notes.kt:40)
//	at com.vav.KotlinInAction.Chapter8.NotesKt.main(Notes.kt:32)
//	at com.vav.KotlinInAction.Chapter8.NotesKt.main(Notes.kt)


