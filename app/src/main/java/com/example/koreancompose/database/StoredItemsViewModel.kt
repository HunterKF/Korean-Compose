package com.example.koreancompose.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoredItemsViewModel(application: Application): AndroidViewModel(application) {
    var readAllData: LiveData<List<StoredItem>>
    private var repository: StoredItemsRepository

    init {
        val storedItemsDb = StoredItemDatabase.getDatabase(application)
        val storedItemDao = storedItemsDb.userDao()
        repository = StoredItemsRepository(storedItemDao)

        readAllData = repository.readAllData
    }
    fun addStoredItem(storedItem: StoredItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStoredItem(storedItem)
        }
    }
//    fun deleteStoredItem(item: String) {
//        repository.deleteStoredItem(item)
//    }
}