package org.example.LRUCache

import java.util.TreeMap

class LRUCacheWithTTL {
                // key -> (expiry -> value)
    val cache = LinkedHashMap<String, Pair<Int, String>>()

    fun put(key: String, value: String, timestamp: String, ttl: String) {
        
    }
}