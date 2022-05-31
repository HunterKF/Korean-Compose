package com.example.koreancompose.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.koreancompose.translate.TranslateViewModel
import com.example.koreancompose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TranslateScreen() {
    Column {
        var translateViewModel by remember { mutableStateOf(TranslateViewModel()) }
        val textToTranslate = "안녕하세요, 저는 헌터입니다. 개발자가 되려고 코딩을 공부하고 있습니다."
        Text(textToTranslate)
        Text(translateViewModel.translatedTextTVM.value)

        Button(onClick = {
                Log.d("BUTTON", "6 The code has started.")
                translateViewModel.translate(textToTranslate).toString()
//                translatedText.value = translateViewModel.translatedTextTVM.value
                Log.d("BUTTON","9 From BUTTON: New value is ${translateViewModel.translatedTextTVM.value}")

        }) {
            Text("Translate")
        }
        Button(onClick = {
            TranslateViewModel().checkIfModelIsDownloaded()
        }) {
            Text("Check model")
        }
    }
}

