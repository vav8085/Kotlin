package com.vav.KotlinInAction

import java.util.Comparator


fun main() {
    sorting()
}

fun sorting() {
    val numbers = listOf(1, 2, 3, 4, 5)

    val names = listOf("Zelda", "Amber", "Vaibhav")

    val sortedNames = names.sorted()

    println(sortedNames)

    val people = listOf(
        Person("Charlie", 30),
        Person("Alice", 25),
        Person("Bob", 35)
    )
    val sortedPeople = people.sorted()
    println(sortedPeople)

    val sortedPeopleWithComparator = people.sortedWith(compareByAge)


}

data class Person(val name: String, val age: Int): Comparable<Person>{
    override fun compareTo(other: Person): Int {
        return this.name.compareTo(other.name)
    }
}

val compareByAge = object: Comparator<Person>{
    override fun compare(person1: Person, person2: Person): Int {
        return person1.age.compareTo(person2.age)
    }

}
