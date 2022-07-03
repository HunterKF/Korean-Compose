package com.example.koreancompose.screens.practicescreen.CustomItemFuns

import android.content.Context
import android.content.Intent
import android.provider.Settings.ACTION_DISPLAY_SETTINGS
import android.provider.Settings.ACTION_HARD_KEYBOARD_SETTINGS
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.example.koreancompose.R
import com.example.koreancompose.ui.theme.PrimaryOrange

@Composable
fun ShareFun(context: Context, shareIntent: Intent) {
    IconButton(
        onClick = {
            context.startActivity(shareIntent)
        }) {
        Icon(
            tint = PrimaryOrange,
            painter = painterResource(id = R.drawable.ic_baseline_share_24),
            contentDescription = "Share"
        )
    }
}