package org.example.LRUCache

class LRUCache {
    val cache = LinkedHashMap<String, String>()

    val capacity = 3

    fun get(key: String): String? {
        //remove the entry from the cache.
        val value = cache.remove(key)
        //pat it back at the front
        cache[key] = value!!

        return value
    }

    fun put(key: String, value: String){
        //check if exists
        if(cache.contains(key)){
            //if exists then remove from current position and put it back at the front
            val value = cache.remove(key)
        }else{
            if(cache.size >= capacity){
                //if cache is full then remove lru entry and add current entry
                //first is lru and last is mru
                val lruKey = cache.keys.first()
                cache.remove(lruKey)
            }
        }
        cache[key] = value
    }



}