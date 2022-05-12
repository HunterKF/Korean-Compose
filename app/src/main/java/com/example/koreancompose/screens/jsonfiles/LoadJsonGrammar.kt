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
    }

    viewModel.allGrammar = loadGrammar()!!
    //Change the values of grammar on start
    val randGrammar = viewModel.allGrammar.let { viewModel.returnGrammar(it) }
    //Grammar - Takes the data class from returnGrammar and then populates the states in the viewModel.
    viewModel.grammarFieldState.value = randGrammar.grammar
    viewModel.grammarInD1State.value = randGrammar.gramInDepth1
    viewModel.grammarInD1ExKor.value = randGrammar.inDepth1ExKor
    viewModel.grammarInD1ExEng.value = randGrammar.inDepth1ExEng
    viewModel.grammarInD2State.value = randGrammar.gramInDepth2
    viewModel.grammarInD2ExKor.value = randGrammar.inDepth2ExKor
    viewModel.grammarInD2ExEng.value = randGrammar.inDepth2ExEng
}