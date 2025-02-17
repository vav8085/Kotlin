package com.vav.KotlinInAction.Chapter2

class Enum {

}

enum class Color(val r: Int,val g: Int,val b: Int){
    GREEN(10,20,30),
    BLUE(10,20,30),
    RED(10,20,30),
    YELLOW(10,20,30);

    val rgb = r + g + b
    fun printRGB() = println("rgb is $rgb")
}