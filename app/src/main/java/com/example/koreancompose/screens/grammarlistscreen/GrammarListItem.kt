package com.example.koreancompose.screens.grammarlistscreen

import com.example.koreancompose.model.ModelGrammar
import com.example.koreancompose.model.ModelWord

data class GrammarListItem(
    val dataGrammar: ArrayList<ModelGrammar>,
    var expandedState: Boolean
)
