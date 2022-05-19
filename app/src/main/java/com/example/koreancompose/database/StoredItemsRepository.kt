package com.example.koreancompose.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class StoredItemsRepository(private val storedItemDao: StoredItemsDao) {
    val readAllData: LiveData<List<StoredItem>> = storedItemDao.getAllItems()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    suspend fun addStoredItem(storedItem: StoredItem) {
        storedItemDao.insert(storedItem)
    }
//    fun deleteStoredItem(item: String) {
//        coroutineScope.launch(Dispatchers.IO) {
//            storedItemDao.delete(item)
//        }
//    }





}