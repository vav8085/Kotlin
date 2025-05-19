package com.vav.KotlinDesignPatterns.CreationalPatterns.SingletonPattern

/**
 *  #   The idea behind singleton pattern is:
 *      1.  We should have only one instance of a class
 *      2.  This instance should be accessible from anywhere in a scope.
 *      3.  Kotlin singletons cannot have constructors, for member initialization use init{} block
 */

object ListSingleton : List<String>{
    override fun contains(element: String): Boolean {
        return this.contains(element)
    }

    override fun containsAll(elements: Collection<String>): Boolean {
        return this.containsAll(elements)
    }

    override val size: Int
        get() = 5

    override fun get(index: Int): String {
        return this[index]
    }

    override fun isEmpty(): Boolean {
        return this.isEmpty()
    }

    override fun iterator(): Iterator<String> {
        return this.iterator()
    }

    override fun listIterator(): ListIterator<String> {
        return this.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<String> {
        return this.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<String> {
        return this.subList(fromIndex,toIndex)
    }

    override fun lastIndexOf(element: String): Int {
        return this.lastIndexOf(element)
    }

    override fun indexOf(element: String): Int {
        return this.indexOf(element)
    }
}

fun main() {
    val listSingleton1 = ListSingleton
    val listSingleton2 = ListSingleton
    println(listSingleton2 === listSingleton1)

}