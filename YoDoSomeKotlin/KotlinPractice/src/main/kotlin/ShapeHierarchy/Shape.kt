package org.example.ShapeHierarchy

import kotlin.math.PI
import kotlin.math.sqrt

abstract class Shape {
    abstract val name: String
    abstract fun calculateArea(): Double
    abstract fun calculatePerimeter(): Double
}

class Circle(val radius: Double): Shape() {
    override val name: String
        get() = "Circle"

    override fun calculateArea(): Double {
        return PI * radius * radius
    }

    override fun calculatePerimeter(): Double {
        return 2 * PI * radius
    }
}

class Rectangle(val height: Double, val width: Double, override val name: String): Shape(){
    override fun calculateArea(): Double {
        return width * height
    }

    override fun calculatePerimeter(): Double {
        return 2 * (width + height)
    }
}

class Triangle(val sideA: Double, val sideB: Double, val sideC: Double): Shape(){
    init {
        //throws illegal argument exception if false
        require(sideA < sideB + sideC && sideB < sideC + sideA && sideC < sideA + sideB)
    }
    override val name: String
        get() = "Triangle"

    override fun calculateArea(): Double {
        val s = calculatePerimeter() / 2
        return sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }

    override fun calculatePerimeter(): Double {
        return sideA + sideB + sideC
    }
}

fun printShapeDetails(shapes: List<Shape>){
    shapes.forEach { shape ->
        println(shape.name)
        println(shape.calculateArea())
        println(shape.calculatePerimeter())
    }
}

fun main(){
    val circle = Circle(5.0)
    val rectangle = Rectangle(10.0, 20.0, "Rectangle")
    val triangle = Triangle(10.0, 20.0, 40.0)

    val shapes = listOf(circle, rectangle, triangle)

    printShapeDetails(shapes)
}