package com.vav.KotlinInAction.Chapter4.archive.code

/*
    When we inherit from a class and override its methods our code
    becomes dependent on this class and these methods.

    When new methods are added to the base class or existing methods
    and changed then our code can break or perform different.

    In kotlin thats why all classes are final by default. you have
    to explicitly mention classes and open

    So you can extend from open classes and if any change is made to
    the base class then you have to make sure child classes are updated
    to avoid any issues in the child classes.

    **********
    But sometimes you want to add methods to the classes which are not open.
    How do we do this?
    One way to achieve this is through composition instead of inheritance.
    An example of this is decorator pattern.

    The way to achieve this is by implementing the same interface that the
    final class that we want to add methods to is implementing in another class.
    So interface I is implemented by final class F and another class B

    Now B will keep an instance of final class F and in all the methods that dont need
    a change B will simply call methods of this final class F.
 */
class ClassesDelegation<T>: Collection<T> {
    private val arrayList = arrayListOf<T>()
    override val size: Int
        get() = arrayList.size

    override fun isEmpty(): Boolean {
        return arrayList.isEmpty()
    }

    override fun iterator(): Iterator<T> {
        return arrayList.iterator()
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return arrayList.containsAll(elements)
    }

    override fun contains(element: T): Boolean {
        return arrayList.contains(element)
    }
}

/*
    In Kotlin we can avoid all the boilerplate code above using "by" keyword
 */

class CollectionDelegate<T>(arrayList: ArrayList<T> = arrayListOf()): Collection<T> by arrayList

class CountingSet<T>(private val mutableSet: MutableSet<T> = mutableSetOf()): MutableCollection<T> by mutableSet{
    var objectsCount = 0
    override fun add(element: T): Boolean {
        objectsCount++
        return mutableSet.add(element)
    }
}

fun main(){
    val countingSet = CountingSet<Int>(mutableSetOf())
    countingSet.add(2)
    countingSet.add(3)
    println(countingSet.objectsCount)
    //2
}