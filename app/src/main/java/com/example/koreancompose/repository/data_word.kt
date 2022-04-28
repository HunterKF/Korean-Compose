package com.example.koreancompose.repository

import com.example.koreancompose.model.PracticeCard

class data_word {
    fun getAllData(): List<PracticeCard> {
        return listOf(
            PracticeCard(
                word = "가다",
                grammar = "으려고",
                inputedSentence = "저는 집으려고 가려고 했는데 제 와이프가 한잔만 하자고 했었어요."
            )
        )
    }
}