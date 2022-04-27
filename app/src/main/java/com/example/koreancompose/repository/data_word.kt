package com.example.koreancompose.repository

import com.example.koreancompose.model.Card

class data_word {
    fun getAllData(): List<Card> {
        return listOf(
            Card(
                word = "가다",
                grammar = "으려고",
                example = "저는 집으려고 가려고 했는데 제 와이프가 한잔만 하자고 했었어요."
            )
        )
    }
}