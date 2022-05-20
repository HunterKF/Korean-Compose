package com.example.koreancompose.screens.favoritesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.koreancompose.database.StoredItem

@Composable
fun FavoriteItem(sentence: String, word: String, grammar: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Text(
            fontWeight = FontWeight.Light,
            text = sentence

        )
    }
}