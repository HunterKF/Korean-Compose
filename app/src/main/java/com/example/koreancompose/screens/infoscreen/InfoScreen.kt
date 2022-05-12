package com.example.koreancompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.koreancompose.screens.infoscreen.InfoSentence

@Composable
fun InfoScreen(
    textFieldState: String?,
    word: String?,
    def: String?,
    wordExKor1: String?,
    wordExEng1: String?,
    wordExKor2: String?,
    wordExEng2: String?,
//    all grammar string
    grammar: String?,
    gramInDepth1: String?,
    inDepth1ExKor: String?,
    inDepth1ExEng: String?,
    gramInDepth2: String?,
    inDepth2ExKor: String?,
    inDepth2ExEng: String?
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        if (textFieldState != null) {
            InfoSentence(textFieldState)
        }
        InfoWord(word, def, wordExKor1, wordExEng1, wordExKor2, wordExEng2)
        InfoGrammar(grammar, gramInDepth1, inDepth1ExKor, inDepth1ExEng, gramInDepth2, inDepth2ExKor, inDepth2ExEng)
    }
}