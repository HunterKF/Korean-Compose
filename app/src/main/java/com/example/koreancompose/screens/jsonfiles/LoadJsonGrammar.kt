package com.example.koreancompose.screens.jsonfiles

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.koreancompose.LoadJSON
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.viewModel

@Composable
fun LoadJsonGrammar() {
    val context = LocalContext.current
    val loadJSON = LoadJSON()
    fun loadGrammar(): ModelJSONGrammar? {
        loadJSON.loadGrammarJson(context)
        return loadJSON.grammarList
        Log.d("LoadJsonWord()", "JSON grammar has been fired")

    }
    viewModel.allGrammar = loadGrammar()!!
}