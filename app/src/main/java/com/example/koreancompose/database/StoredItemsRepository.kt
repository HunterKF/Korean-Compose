package com.example.koreancompose.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class StoredItemsRepository(private val storedItemDao: StoredItemsDao) {
    val readAllData: LiveData<List<StoredItem>> = storedItemDao.getAllItems()

    val searchResults = MutableLiveData<List<StoredItem>>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    suspend fun addStoredItem(storedItem: StoredItem) {
        storedItemDao.insert(storedItem)
    }

    suspend fun deleteStoredItem(storedItem: StoredItem) {
        storedItemDao.delete(storedItem)
    }

    fun deleteAllStoredItem() {
        coroutineScope.launch(Dispatchers.IO) {
            storedItemDao.deleteAll()
        }
    }
    fun deleteOne(key: String) {
        coroutineScope.launch(Dispatchers.IO) {
            storedItemDao.delete(key)
        }
    }


    suspend fun searchStoredItem(sentence: String): Flow<List<StoredItem>> {
        return storedItemDao.searchStoredItem(sentence)
    }








}