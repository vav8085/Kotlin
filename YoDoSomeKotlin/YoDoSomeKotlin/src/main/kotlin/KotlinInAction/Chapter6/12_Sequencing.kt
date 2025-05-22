package com.vav.KotlinInAction.Chapter6

/*
    asSequence()    -   Works similar to java streams. Converts collections to sequence which avoids creation of intermediate
                        collections when multiple processing methods like filter{} map{} etc are chained.
                        Sequence can improve performance when lists are large
                        Elements of Sequences are evaluated lazily.
                        In the end, we have to convert a sequence back to Collection.
 */

fun main(){
    sequenceFx()
}

fun sequenceFx(){
    println(personAge.map { it.age }.filter { it > 20 })
    println(personAge.asSequence().map { it.age }.filter { it > 20 }.toList())
}
//[33, 22, 21, 21]
//[33, 22, 21, 21]