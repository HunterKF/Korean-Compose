package com.example.koreancompose.screens.wordlistscreen

import androidx.compose.runtime.MutableState
import com.example.koreancompose.model.ModelWord

data class WordListItem(
    val dataWords: ArrayList<ModelWord>,
    var expandedState: Boolean
)
