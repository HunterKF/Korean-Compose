package com.example.koreancompose.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.koreancompose.translate.TranslateViewModel
import com.example.koreancompose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TranslateScreen() {
    Column {
        val translateViewModel = TranslateViewModel()
        val textToTranslate = "안녕하세요, 저는 헌터입니다. 개발자가 되려고 코딩을 공부하고 있습니다."
        val translatedText = remember { mutableStateOf("") }
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        Text(textToTranslate)
        TranslatedText(text = viewModel.translatedText.value)
        Text(translatedText.value)
        Button(onClick = {
            coroutineScope.launch {
                delay(3000)
                TranslateViewModel().translate(textToTranslate)
                translatedText.value = translateViewModel.translate(textToTranslate).toString()
                delay(3000)

                translatedText.value = translateViewModel.translatedText.value
                Log.d("BUTTON","From BUTTON: New value is ${viewModel.translatedText.value}")
            }


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

@Composable
fun TranslatedText(text: String) {
    Text(text)
}