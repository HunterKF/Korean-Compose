package com.example.koreancompose.screens.practicescreen.CustomItemFuns

import android.content.Context
import android.content.Intent
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun ShareFun(context: Context, shareIntent: Intent) {
    IconButton(
        modifier = Modifier
            .alpha(ContentAlpha.medium),
        onClick = {
            context.startActivity(shareIntent)
        }) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share"
        )
    }
}