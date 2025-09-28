package org.example.CollectionTransformer

data class User(val id: Int, val name: String, val age: Int, val city: String)

val users = listOf(
    User(1, "Alice", 25, "New York"),
    User(2, "Bob", 17, "Chicago"),
    User(3, "Charlie", 25, "New York"),
    User(4, "David", 21, "Boston"),
    User(5, "Eve", 16, "New York"),
    User(6, "Frank", 35, "New York")
)

fun processUsers(users: List<User>): List<String>{
    return users.filter { it.age >= 18 }.filter { it.city == "New York" }.sortedWith { a, b -> a.name.compareTo(b.name) }.map { "ID: ${it.id}, NAME: ${it.name.uppercase()}" }
}

fun main(){
    println( processUsers(users))
}