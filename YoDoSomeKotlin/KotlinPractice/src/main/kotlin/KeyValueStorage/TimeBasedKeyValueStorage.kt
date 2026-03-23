package org.example.KeyValueStorage

import java.util.TreeMap
import kotlin.collections.emptyList

/*
    Very similar to time based key value store leetcode problem

    Treemap has a floorKey method that can give the floor timestamp of requested timestamp.
    this can help retrieving the floor value if any.

    The other solution can be linear / binary searching the floor value.
 */
class TimeBasedKeyValueStorage {
    // key -> (field -> (timestamp -> value))
    val cache = hashMapOf<String, HashMap<String, TreeMap<Int, String?>>>()

    fun setAt(key: String, field: String, value: String, timestamp: Int) {
        cache.getOrPut(key) { hashMapOf() }.getOrPut(field) { TreeMap() }[timestamp] = value

//        val record = cache.getOrDefault(key, hashMapOf())
//        val history = record.getOrDefault(field, TreeMap())
//        history.put(timestamp, value)
//        record.put(field, history)
//        cache.put(key, record)
    }

    fun getAt(key: String, field: String, timestamp: Int): String? {
        val history = cache[key]?.get(field) ?: return null
        val floorTime = history.floorKey(timestamp) ?: return null
        return history.get(floorTime)
    }

    fun deleteAt(key: String, field: String, timestamp: Int): Boolean {
        val record = cache[key] ?: return false
        val history = record[field] ?: return false
        val floorTime = history.floorKey(timestamp) ?: return false
        if (history[floorTime] != null) {
            history[timestamp] = null
            return true
        } else return false
    }

    fun scanAt(key: String, timestamp: Int): List<String> {
        val record = cache[key] ?: return emptyList()

        val output = mutableListOf<String>()

        //now we dont have a specific field so we will go over all fields
        for(field in record.keys){
            //A field has a value that changes over time timestamp -> value
            //We will be finding the floorkey for this field and add it to output
            //if no data for this fields history then continue
            val history = record[field] ?: continue

            val value = history[history.floorKey(timestamp)] ?: continue
            output.add("$field($value)")
        }
        return output.sortedWith { a,b -> a.compareTo(b) }.toList()
    }

    fun scanByPrefixAt(key: String, prefix: String, timestamp: Int): List<String> {
        val record = cache[key] ?: return emptyList()

        val output = mutableListOf<String>()

        for(field in record.keys){
            if(!field.startsWith(prefix)) continue

            val history = record[field] ?: continue

            val value = history[history.floorKey(timestamp)] ?: continue
            output.add("$field($value)")
        }
        return output.sortedWith { a,b -> a.compareTo(b) }.toList()
    }

}