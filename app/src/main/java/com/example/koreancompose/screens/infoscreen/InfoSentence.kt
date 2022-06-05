package com.example.koreancompose.screens.infoscreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koreancompose.PapagoDTO
import com.example.koreancompose.translate.TranslateViewModel
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.checkerframework.common.returnsreceiver.qual.This
import org.json.JSONObject
import java.io.IOException

@Composable
fun InfoSentence(textFieldState: String) {
    var translateViewModel by remember { mutableStateOf(TranslateViewModel()) }

//This is the function to use papago. Everything works, but don't turn it on unless you really have to.
//    translateViewModel.translateText(textFieldState)

    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Medium,
            text = "$textFieldState"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Medium,
            text = translateViewModel.translatedTextTVM.value
        )

    }

}