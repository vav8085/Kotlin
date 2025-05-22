package com.vav.KotlinInAction.Chapter6

/*
    asSequence()    -   Works similar to java streams. Converts collections to sequence which avoids creation of intermediate
                        collections when multiple processing methods like filter{} map{} etc are chained.
                        Sequence can improve performance when lists are large
                        Elements of Sequences are evaluated lazily.
                        In the end, we have to convert a sequence back to Collection.
                        Lazy evaluation happens at a later point in time.
                        Instead of doing a map first and create a list to send as input to filter, we process each value
                        from input and apply map and filter to it so there is no intermediate collection.
                        e.g. for 1.2.3.4.5 if we apply map {it * 2} and then find {it > 5}
                        we will apply map to 1 and then filter to 1 * 1
                        we will apply map to 2 and then filter to 2 * 2
                        ...
                        This is different from collections where we will first apply map to entire input and then filter to result.

                        Sometimes order of operations may result in same output but optimizations can be made
                        by calling some reducing operations first that can make collection smaller and then
                        applying operation which apply to entire collection.
 */

fun main(){
    sequenceFx()
}

fun sequenceFx(){
    println(personAge.map { it.age }.filter { it > 20 })
    println(personAge
        .asSequence()
        .map { it.age }
        .filter { it > 20 } // intermediate sequence operation. Always Lazy
        .toList())  //terminal sequence operation.
}
//[33, 22, 21, 21]
//[33, 22, 21, 21]