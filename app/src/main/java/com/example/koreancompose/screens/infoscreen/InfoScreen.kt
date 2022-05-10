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
    wordDef: String?,
    grammar: String?,
    grammarDef: String?,
    grammarExample: String?
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
        InfoWord(word, wordDef)
        InfoGrammar(grammar, grammarDef, grammarExample)
    }
}