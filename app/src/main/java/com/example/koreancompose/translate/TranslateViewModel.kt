package com.example.koreancompose.translate

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.koreancompose.ViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.*

class TranslateViewModel() {
    private val translator: Translator
            = Translation.getClient(
        TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.KOREAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
    )

    fun checkIfModelIsDownloaded() {
        val downloadConditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        this.translator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener { println("Check model was successful!") }
            .addOnFailureListener { println("Check model was a failure!") }
    }

    var translatedText = mutableStateOf("")

    fun translate(text: String){
        Log.d("BUTTON from translate()", "1 $text")

        this.translator.translate(text)
            .addOnSuccessListener { result -> translatedText.value = result
                Log.d("BUTTON from translate()", "2 ${translatedText.value}")
                Log.d("BUTTON from translate()", "3 ${result}")
            }
        Log.d("BUTTON from translate()", "4 ${translatedText.value}")

    }





}