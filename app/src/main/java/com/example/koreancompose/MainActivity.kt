package com.example.koreancompose

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.koreancompose.screens.jsonfiles.LoadJsonGrammar
import com.example.koreancompose.screens.jsonfiles.LoadJsonWords
import com.example.koreancompose.translate.TranslateViewModel
import com.example.koreancompose.ui.theme.KoreanComposeTheme
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import java.io.IOException


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









