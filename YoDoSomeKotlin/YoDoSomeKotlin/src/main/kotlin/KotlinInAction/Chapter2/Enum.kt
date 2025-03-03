package com.vav.KotlinInAction.Chapter2

fun main(){
    //All Enums are constants
    //When we create an Enum class the constants like RED, BLUE.. can be accessed directly using
    //class name, just like in java.
    //Enums can have additional properties which can give additional information about them.
    //Enums can also have calculated properties like rgb here.
    //Enums can have methods
    val color = Color.RED
}
enum class Color(val r: Int,val g: Int,val b: Int){
    GREEN(10,20,30),
    BLUE(10,20,30),
    RED(10,20,30),
    YELLOW(10,20,30);

     val rgb = r + g + b
    fun printRGB() = println("rgb is $rgb")
}