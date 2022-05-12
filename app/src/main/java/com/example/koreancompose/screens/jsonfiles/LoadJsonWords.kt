package com.example.koreancompose.screens.jsonfiles

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.koreancompose.LoadJSON
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.viewModel

@Composable
fun LoadJsonWords() {
    val context = LocalContext.current
    val loadJSON = LoadJSON()
    fun loadWord(): ModelJSONWord? {
        loadJSON.loadWordJson(context)
        return loadJSON.wordList
    }

    viewModel.allWords = loadWord()!!
}