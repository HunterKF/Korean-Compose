package com.example.koreancompose.translate

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TranslateViewModel() {
    private val translator: Translator
            = Translation.getClient(
        TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.KOREAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
    )
    var translatedTextTVM = mutableStateOf("Loading...")

    fun checkIfModelIsDownloaded() {
        val downloadConditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        this.translator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener { println("Check model was successful!") }
            .addOnFailureListener { println("Check model was a failure!") }
    }



    fun translate(text: String){
        Log.d("BUTTON from translate()", "1 $text")

        this.translator.translate(text)
            .addOnSuccessListener { result -> translatedTextTVM.value = result
                Log.d("BUTTON from translate()", "2 ${translatedTextTVM.value}")
                Log.d("BUTTON from translate()", "3 ${result}")
            }
        Log.d("BUTTON from translate()", "4 ${translatedTextTVM.value}")

    }





}