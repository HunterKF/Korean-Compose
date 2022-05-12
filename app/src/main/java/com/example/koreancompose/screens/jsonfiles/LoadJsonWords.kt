package com.example.koreancompose.screens.jsonfiles

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.koreancompose.LoadJSON
import com.example.koreancompose.TAG
import com.example.koreancompose.ViewModel
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
    //Change the values of word on start
    val randWord = viewModel.allWords.let { viewModel.returnWord(it) }
    viewModel.wordFieldState.value = randWord.word
    viewModel.wordDefFieldState.value = randWord.def
    viewModel.wordExKor1.value = randWord.wordExKor1
    viewModel.wordExEng1.value = randWord.wordExEng1
    viewModel.wordExKor2.value = randWord.wordExKor2
    viewModel.wordExEng2.value = randWord.wordExEng2
}