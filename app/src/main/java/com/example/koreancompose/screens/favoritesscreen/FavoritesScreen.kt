package com.example.koreancompose.screens.favoritesscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.koreancompose.database.StoredItem

@Composable
fun FavoritesScreen() {
    Surface(modifier = Modifier.fillMaxSize()){

    }
    Text("Hello")
}

@Preview(showSystemUi = true)
@Composable
fun preview() {
    FavoritesScreen()
}