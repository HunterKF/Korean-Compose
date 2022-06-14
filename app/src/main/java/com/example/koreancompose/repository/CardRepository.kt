package com.example.koreancompose.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import com.example.koreancompose.MainActivity
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.model.PracticeCard
import java.io.InputStream
import java.lang.Exception

import com.google.gson.Gson


class CardRepository {

    var allCards = mutableStateListOf<PracticeCard>(
    )

    fun getAllData(): List<PracticeCard> {

        return allCards
    }



}