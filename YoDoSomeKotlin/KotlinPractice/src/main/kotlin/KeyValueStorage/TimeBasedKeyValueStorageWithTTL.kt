package org.example.KeyValueStorage

import java.util.TreeMap

/*
    Record has fields and fields have versions with timestamps. Each version also has a ttl.
     so if ttl expires then that version is no longer valid
 */
class TimeBasedKeyValueStorageWithTTL {

    val cache = hashMapOf<String, HashMap<String, TreeMap<Int, Pair<String, Int?>?>>>()

    fun setAtWithTtl(key: String, field: String, value: String, timestamp: Int, ttl: Int) {
        val history = cache.getOrPut(key) { hashMapOf() }.getOrPut(field) { TreeMap() }
        history[timestamp] = Pair(value, timestamp + ttl)
    }

    fun getAt(key: String, field: String, timestamp: Int): String? {
        val record = cache.get(key) ?: return null
        val history = record.get(field) ?: return null
        val floorTime = history.floorKey(timestamp) ?: return null
        val floorValue = history.get(floorTime) ?: return null
        val expiry = floorValue.second
        return if(expiry != null && timestamp < expiry) floorValue.first else null
    }

    fun deleteAt(key: String, field: String, timestamp: Int): Boolean {
        val record = cache.get(key) ?: return false
        val history = record.get(field) ?: return false
        val floorTime = history.floorKey(timestamp) ?: return false
        //Now timestamp can be 3 and floor time can be 1 in case existing records are 1 5 7..
        //Now we dont delete the floor value if we couldnt find the timestamp
        //this is because if next query ask for timestamp 2 then it will get nothing, which is incorrect
        //we instead insert null to 3 this way we also get a null for 4 but we still get value ofr 2

        //but we do above only if we have a floor value, otherwise just return false

        if(history[floorTime] == null) return false

        return if(history[floorTime]?.first != null){
            history[timestamp] = null
            true
        } else false
    }

    fun scanAt(key: String, timestamp: Int): List<String> {
        val record = cache[key] ?: return emptyList()

        //now again we have to go over all the fields
        //there shouldnt be any use of ttl here

        val output = mutableListOf<String>()

        for(field in record.keys){
            val history = record[field] ?: continue

            //floorKey can return null. if it returns null then there is no floor timestamp
            val floorTime = history.floorKey(timestamp) ?: continue

            val floorPair = history[floorTime] ?: continue

            // now we will return all the values which are less than ttl
            val expiry = floorPair.second

            if(expiry != null && timestamp >= expiry) continue

            output.add("${field}(${floorPair.first})")
        }
        return output.sortedWith { a, b -> a.compareTo(b) }
    }

    fun scanByPrefixAt(key: String, prefix: String, timestamp: Int): List<String> {

    }
}