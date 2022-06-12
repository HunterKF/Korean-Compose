package com.example.koreancompose.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StoredItemsDao {
    //ONCONFLICT IGNORE DOESN'T WORK, BUT REPLACE DOES... Why?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: StoredItem)

    @Update
    suspend fun update(item: StoredItem)

    @Delete
    suspend fun delete(item: StoredItem)
    //I use this
    @Query("DELETE FROM stored_items_table")
    suspend fun deleteAll()
    //I use this
    @Query("SELECT * FROM stored_items_table WHERE sentence = :inputtedSentence LIMIT 1")
    fun searchStoredItem(inputtedSentence: String): List<StoredItem>

    //This one works
    @Query("DELETE FROM stored_items_table WHERE sentence = :inputtedSentence")
    suspend fun delete(inputtedSentence: String)
    //I use this???
    @Query("SELECT * FROM stored_items_table WHERE itemId = :key")
    suspend fun get(key: String): StoredItem?
    //I use this
    @Query("SELECT * FROM stored_items_table ORDER BY itemId DESC")
    fun getAllItems(): LiveData<List<StoredItem>>



}