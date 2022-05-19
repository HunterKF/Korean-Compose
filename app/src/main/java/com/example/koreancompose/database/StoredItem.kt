package com.example.koreancompose.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stored_items_table")
class StoredItem (
    @PrimaryKey
    val itemId: Long = 0L,

    @ColumnInfo
    val inputtedWord: String = "",

    @ColumnInfo
    val inputtedGrammar: String = "",

    @ColumnInfo
    val inputtedSentence: String = ""

)