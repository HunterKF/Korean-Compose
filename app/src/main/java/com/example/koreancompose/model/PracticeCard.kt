package com.example.koreancompose.model

data class PracticeCard(
    val word: String,
    val wordDef: String,
//    val wordExampleKorean1: String,
//    val wordExampleEnglish1: String,
//    val wordExampleKorean2: String,
//    val wordExampleEnglish2: String,
    val grammar: String,
    val grammarDef: String,
    val grammarExample: String,
//    val grammarExplanation1ExampleKorean: String,
//    val grammarExplanation1ExampleEnglish: String,
    val inputtedSentence: String
)
