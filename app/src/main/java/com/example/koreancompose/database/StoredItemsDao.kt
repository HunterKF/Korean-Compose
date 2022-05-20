package com.example.koreancompose.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StoredItemsDao {
    //ONCONFLICT IGNORE DOESN'T WORK, BUT REPLACE DOES... Why?
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: StoredItem)

    @Update
    suspend fun update(item: StoredItem)

//    @Delete
//    suspend fun delete(item: String)

    @Query("SELECT * FROM stored_items_table WHERE itemId = :key")
    suspend fun get(key: Long): StoredItem?

    @Query("SELECT * FROM stored_items_table ORDER BY itemId DESC")
    fun getAllItems(): LiveData<List<StoredItem>>


}