package org.example.GenericInventorySystem

interface Storable {
    val name: String
}

data class Book(override val name: String, val author: String): Storable
data class Tool(override val name: String, val itemCode: String): Storable

class Inventory<T: Storable> {
    private items: 
}