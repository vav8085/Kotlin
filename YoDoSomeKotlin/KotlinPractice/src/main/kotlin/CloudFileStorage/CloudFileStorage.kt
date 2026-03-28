package org.example.CloudFileStorage

class CloudFileStorage {
    val storage = hashMapOf<String, HashSet<String>>()

    fun uploadFile(userId: String, fileName: String): String {
        val record =  storage.getOrPut(userId) { hashSetOf() }
        val added = record.add(fileName)
        return if(added) "uploaded" else "overwritten"
    }

    fun getFile(userId: String, fileName: String): String?{
        val record = storage[userId] ?: return null
        return if(record.contains(fileName)) {
            fileName
        }
        else null
    }

    fun deleteFile(userId: String, fileName: String): Boolean{
        val record = storage[userId] ?: return false
        return if(record.contains(fileName)) {
            record.remove(fileName)
            true
        }
        else false
    }
}