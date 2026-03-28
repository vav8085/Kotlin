package org.example.CloudFileStorage

class CloudFileStorageWithFileSizeAndQuotas {

    //user -> (filename -> size)
    val storage = hashMapOf<String, HashMap<String, Int>>()

    //user -> capacity
    val quota = hashMapOf<String, Int>()

    fun addUser(userId: String, capacity: Int): Boolean {
        if (userId in quota) return false
        storage[userId] = hashMapOf()
        quota[userId] = capacity
        return true
    }

    fun uploadFile(userId: String, fileName: String, size: Int): String {
        if (!storage.contains(userId) || !quota.contains(userId)) return "failed"

        val record = storage.getOrDefault(userId, hashMapOf())

        var totalSize = 0

        for (key in record.keys) totalSize += record[key] ?: 0


        val capacity = quota[userId] ?: 0

        if (record.contains(fileName)) {
            val existingFileSize = record.getOrDefault(fileName, 0)
            if (totalSize - existingFileSize + size <= capacity) {
                record[fileName] = size
                storage[userId] = record
                return "overwritten"
            } else {
                return "failed"
            }
        } else {
            if (size + totalSize <= capacity) {
                record[fileName] = size
                storage[userId] = record
                return "uploaded"
            } else return "failed"
        }
    }

    fun getFile(userId: String, fileName: String): String? {
        val record = storage.get(userId) ?: return null

        if (fileName in record.keys) {
            val size = record[fileName] ?: return null
            return "$fileName(${size})"
        } else return null
    }

    fun deleteFile(userId: String, fileName: String): Boolean {
        val record = storage[userId] ?: return false
        if(fileName in record) {
            record.remove(fileName)
            return true
        }else return  false
    }
}