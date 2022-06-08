package com.example.koreancompose.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stored_items_table")
class StoredItem (
    @PrimaryKey(autoGenerate = true)
    val itemId: Long = 0L,

    @ColumnInfo(name = "word")
    val inputtedWord: String = "",

    @ColumnInfo(name = "grammar")
    val inputtedGrammar: String = "",

    @ColumnInfo(name = "sentence")
    val inputtedSentence: String = "",

    @ColumnInfo(name = "favorited")
    val isFavorite: Boolean

)