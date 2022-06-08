package com.example.koreancompose.database

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoredItemsViewModel(application: Application): AndroidViewModel(application) {
    var readAllData: LiveData<List<StoredItem>>
    private var repository: StoredItemsRepository

    val searchResults: MutableLiveData<List<StoredItem>>


    init {
        val storedItemsDb = StoredItemDatabase.getDatabase(application)
        val storedItemDao = storedItemsDb.userDao()
        repository = StoredItemsRepository(storedItemDao)

        readAllData = repository.readAllData
        searchResults = repository.searchResults
    }
    fun addStoredItem(storedItem: StoredItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStoredItem(storedItem)
        }
    }
    fun deleteStoredItem(storedItem: StoredItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStoredItem(storedItem)
        }
    }

    fun searchStoredItem(sentence: String) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.searchStoredItem(sentence)
        }
    }

    fun deleteOne(key: String) {
        repository.deleteOne(key)
    }
    fun deleteAllStoredItem() {
        repository.deleteAllStoredItem()
    }

    var favoriteItemState = mutableStateOf(false)

}