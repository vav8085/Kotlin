package com.vav.KotlinInAction.Chapter2.archive.code.Shape

import com.vav.KotlinInAction.Chapter2.code.Geometry.Area

/*
    Notice that when we have class in the same package we can use it directly without importing

    If we have to use a class from a different package, then we have to import it
 */
class Rectangle_hasNotes {
    fun findArea(){
        //we had to import area because it was in a different package
        val area = Area()

        //but for circle we did not need to import anything
        val  circle = Circle()
    }
}