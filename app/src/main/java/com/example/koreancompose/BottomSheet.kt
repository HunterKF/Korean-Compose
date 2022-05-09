package com.example.koreancompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BottomSheet() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Word()
        Grammar()
    }
}