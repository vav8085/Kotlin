package com.vav

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
}


//val words = listOf("eat", "tea", "tan", "ate", "nat", "bat")
//
//// Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]


fun groupAnagrams(anagrams: List<String>): List<String>{
    val output = mutableListOf<String>()
    val map = hashMapOf<String, MutableList<String>>()

    for(word in anagrams){
        val chArr = word.toCharArray()
        val sorted = chArr.sorted().toString()

        val list = map.getOrDefault(sorted, mutableListOf())

        list.add(word)
        map.put(sorted, list)
    }

    val keys = map.keys

    
}