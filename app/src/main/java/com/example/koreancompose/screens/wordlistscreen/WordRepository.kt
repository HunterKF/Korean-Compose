package com.example.koreancompose.screens.wordlistscreen

import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.model.ModelWord

class WordRepository {
    val allWords = mutableListOf<ModelJSONWord>()

    fun getAllWordData(): ArrayList<ModelJSONWord> {
        return allWords as ArrayList<ModelJSONWord>
    }
}