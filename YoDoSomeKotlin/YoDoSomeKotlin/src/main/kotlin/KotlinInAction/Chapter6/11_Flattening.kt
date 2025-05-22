package com.vav.KotlinInAction.Chapter6

/*
        flatmap{} - Does 2 things:
                    1.  transforms a collection as per lambda just like map
                    2.  Flattens the hierarchy
                    Note: Flatmap will flatten one level hierarchy in a single call. You have to flatten again
                    if you have more nesting.

        flatten -   Flattens multiple hierarchy. No transformation

 */

fun main(){
    mapFx()
    separator()
    flatMapFx()
    separator()
    flattenFx()
}

data class Book(val title: String, val authors: List<String>)

val books = listOf(Book("Kotlin In Action", listOf("Isakova", "Elizarov", "Aigner", "Jemerov")),
    Book("Atomic Kotlin", listOf("Eckel", "Isakova")), Book("Thinking In Java", listOf("Eckel")))

fun mapFx(){
    println(books.map { it.authors })
}
//[[Isakova, Elizarov, Aigner, Jemerov], [Eckel, Isakova], [Eckel]]

fun flatMapFx(){
    println(books.flatMap { it.authors })
}
//[Isakova, Elizarov, Aigner, Jemerov, Eckel, Isakova, Eckel]

fun flattenFx(){
    println(books.map { it.authors }.flatten())
}
//[Isakova, Elizarov, Aigner, Jemerov, Eckel, Isakova, Eckel]