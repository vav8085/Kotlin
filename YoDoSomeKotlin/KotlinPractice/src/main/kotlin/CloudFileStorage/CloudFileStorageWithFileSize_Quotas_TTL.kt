package org.example.CloudFileStorage

class CloudFileStorageWithFileSize_Quotas_TTL {
                            //user -> fileName -> size, timestamp
    val storage = hashMapOf<String, HashMap<String, Pair<String, Int>>>()
                        //user -> capacity
    val quota = hashMapOf<String, Int>()

    fun addUser(userId: String, capacity: Int): Boolean {

    }

    fun uploadFile(userId: String, fileName: String, size: Int): String {

    }

    fun uploadFileWithTtl(userId: String, fileName: String, size: Int, timestamp: Int, ttl: Int): String {

    }

    fun getFile(userId: String, fileName: String, timestamp: Int): String? {

    }

    fun deleteFile(userId: String, fileName: String, timestamp: Int): Boolean {

    }

    fun searchFiles(userId: String, prefix: String, timestamp: Int): List<String> {

    }

    fun rollback(userId: String, timestamp: Int): Boolean {

    }

}