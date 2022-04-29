package com.example.koreancompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.koreancompose.ui.theme.KoreanComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            KoreanComposeTheme {
                PracticeScreen()
            }
        }
    }
}








