package com.example.koreancompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StoredItem::class], version = 3, exportSchema = false)
abstract class StoredItemDatabase : RoomDatabase() {
    abstract fun userDao(): StoredItemsDao

    companion object {
        @Volatile
        private var INSTANCE: StoredItemDatabase? = null

        fun getDatabase(context: Context): StoredItemDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoredItemDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}