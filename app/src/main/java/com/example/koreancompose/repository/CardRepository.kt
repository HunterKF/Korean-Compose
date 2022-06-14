package com.example.koreancompose.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.koreancompose.model.PracticeCard


class CardRepository {

    var allCards = mutableStateListOf<PracticeCard>(
    )

    fun getAllData(): List<PracticeCard> {
        return allCards
    }



}