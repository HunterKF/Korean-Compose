package com.example.koreancompose

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.koreancompose.screens.TranslateScreen
import com.example.koreancompose.screens.jsonfiles.LoadJsonGrammar
import com.example.koreancompose.screens.jsonfiles.LoadJsonWords
import com.example.koreancompose.translate.TranslateViewModel
import com.example.koreancompose.ui.theme.KoreanComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        TranslateViewModel().checkIfModelIsDownloaded()

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


//@Composable
//fun TranslateScreen() {
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    DisposableEffect(Unit) {
//        val lifecycleObserver = LifecycleEventObserver { _, event ->
//            if (event == Lifecycle.Event.ON_STOP) {
//                // close Translator & Recognizer here
//            }
//        }
//
//        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
//        onDispose { lifecycleOwner.lifecycle.removeObserver(lifecycleObserver) }
//    }
//}








