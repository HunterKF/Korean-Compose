package com.example.koreancompose.model

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.MutableStateFlow

data class PracticeCard(
    val inputtedSentence: String,
    val word: String,
    val wordDef: String,
    val wordExampleKorean1: String,
    val wordExampleEnglish1: String,
    val wordExampleKorean2: String,
    val wordExampleEnglish2: String,
    val grammar: String,
    val gramInDepth1: String,
    val inDepth1ExKor: String,
    val inDepth1ExEng: String,
    val gramInDepth2: String,
    val inDepth2ExKor: String,
    val inDepth2ExEng: String,
    var isClicked: MutableState<Boolean>

)
