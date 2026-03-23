package org.example.KeyValueStorage

import java.util.TreeMap

class TimeBasedKeyValueStorageWithTTL {

    val cache = hashMapOf<String, HashMap<String, TreeMap<Int, Pair<String, Int?>>>>()

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
        
    }

    fun scanAt(key: String, timestamp: Int): List<String> {
    }

    fun scanByPrefixAt(key: String, prefix: String, timestamp: Int): List<String> {
    }
}