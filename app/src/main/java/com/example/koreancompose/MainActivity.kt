package com.example.koreancompose

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.screens.jsonfiles.LoadJsonGrammar
import com.example.koreancompose.screens.jsonfiles.LoadJsonWords
import com.example.koreancompose.ui.theme.KoreanComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        super.onCreate(savedInstanceState)
        setContent {

            KoreanComposeTheme {
                LoadJsonWords()
                LoadJsonGrammar()
                Navigation()
            }
        }
    }
}

