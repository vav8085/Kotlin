package org.example.KeyValueStorage

/*
    Basic map with key -> (field -> value)
 */
class KeyValueStorage {
    val cache = hashMapOf<String, HashMap<String, String>>()

    fun set(key: String, field: String, value: String) {
        cache.getOrPut(key) { hashMapOf() }[field] = value
    }

    fun get(key: String, field: String): String? {
        val record = cache[key] ?: return null

        return record[field]
    }

    fun delete(key: String, field: String): Boolean {
        val record = cache[key] ?: return false

        return record.remove(field) != null
    }

    fun scan(key: String): List<String> {
        return cache[key]
            ?.entries
            ?.sortedWith { a, b -> a.key.compareTo(b.key) }
            ?.map { entry -> "${entry.key}(${entry.value})" }
            ?: emptyList()
    }

    fun scanByPrefix(key: String, prefix: String): List<String>{
        return cache[key]?.entries?.filter { entry -> entry.key?.startsWith(prefix) == true }?.sortedWith { a,b -> a.key.compareTo(b.key) }?.map { entry -> "${entry.key}(${entry.value})" }?:emptyList()
    }
}