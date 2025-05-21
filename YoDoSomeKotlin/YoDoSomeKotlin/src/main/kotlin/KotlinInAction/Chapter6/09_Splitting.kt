package com.vav.KotlinInAction.Chapter6

/*
    chunked{} and windowed{}

    windowed{}   -   Used to create sliding window of a specific size. It produces a list of lists where each internal
                     list is a window of given size.
                     Windowed takes 2 params:
                     1. size
                     2. step
                     by default step == 1
                     The initial window created will be of size = size and then it will move forward in steps where step
                     size = step.
                     Note: NOTICE THAT IF YOU DONT WANT REPETITION THEN KEEP SIZE == STEP
                     If we have a list of size 6 and window size = 2 and step = 2 then it will make pair of 01,23,45
                     If we have a list of size 7 which is odd then last element wont have a pair so entire window
                     will be discarded
                     If step > size then everything outside the size will be discarded
                     e.g. 1,2,3,4,5,6,7
                     size = 2 step = 3
                     1,2  ignore3   4,5  ignore6   7 is alone so discarded

    chunked{}   -   chunked splits a list into smaller lists of a specific size. If the last list cannot be of size then
                    chunked will make a smaller list of remaining elements
 */


fun main() {
    windowedFx()
    separator()
    chunkedFx()
}

fun windowedFx() {
    val windowed = listOfIntegers.windowed(2)
    println(windowed)
    val windowedWithSteps = listOfIntegers.windowed(size = 2, step = 2)
    println(windowedWithSteps)
    val newListOfIntegers = listOfIntegers.toMutableList()
    newListOfIntegers.addLast(45) //will be discarded because window is not full.
    println(newListOfIntegers.windowed(size = 2, step = 2))
    newListOfIntegers.addLast(55)
    println(newListOfIntegers.windowed(size = 2, step = 2))
    println(newListOfIntegers.windowed(size = 2, step = 3))
    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    println(list.windowed(size = 2, step = 3))

}
//[[1, 2], [2, 37], [37, 6], [6, 4], [4, 3]]
//[[1, 2], [37, 6], [4, 3]]
//[[1, 2], [37, 6], [4, 3]]
//[[1, 2], [37, 6], [4, 3], [45, 55]]
//[[1, 2], [6, 4], [45, 55]]
//[[1, 2], [4, 5]]


fun chunkedFx() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    println(list.chunked(size = 2))
    println(list.chunked(size = 3))
}
//[[1, 2], [3, 4], [5, 6], [7]]
//[[1, 2, 3], [4, 5, 6], [7]]