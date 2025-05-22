package com.vav.KotlinInAction.Chapter6

/*
    *   Zip{}   -   zip{} can be performed on two related collections to merge their values at the same index
                    into a single collection.
                    If you call zip directly on two lists like list1.zip(list2) then it creates a Pair(element1,element2)
                    for each index.
                    You can also pass a lambda to create a custom data structure like name = listOf(), age = listOf()
                    can create a Person(name,age) data structure.
                    Zip can also be used in infix notation just like 'to'
                    Zip can also be called on multiple lists to combine all their values
                    NOTE - ZIP WILL CREATE THE OUTPUT COLLECTION THE SIZE OF THE SMALLER COLLECTION. IF ONE LIST HAS 5 ELEMENTS,
                           WHILE OTHER ONE HAS ONLY 3 THEN FIRST 3 WILL MAP AND REMAINING 2 OF THE FIRST LIST WILL BE IGNORED.
 */

fun main() {
    zipFx()
    separator()

}

fun zipFx() {
    val names = listOf("Batman", "Flash", "Wonder Woman", "Superman", "Green Lantern")
    val ages = listOf(34, 26, 29)
    val powerLevels = listOf(80, 60, 85, 90, 70)
    println(names.zip(ages))
    println(names zip ages zip powerLevels)
    println(names.zip(ages) { name, age -> Person(name, age) })
    println(
        names.zip(ages)
            .zip(powerLevels) { nameAgePair, powerLevel -> Triple(nameAgePair.first, nameAgePair.second, powerLevel) })
}
//[(Batman, 34), (Flash, 26), (Wonder Woman, 29)]
//[((Batman, 34), 80), ((Flash, 26), 60), ((Wonder Woman, 29), 85)]
//[Person(name=Batman, age=34), Person(name=Flash, age=26), Person(name=Wonder Woman, age=29)]
//[(Batman, 34, 80), (Flash, 26, 60), (Wonder Woman, 29, 85)]
//-----------------------------------------------------------


