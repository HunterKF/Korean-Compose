package com.example.koreancompose

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.koreancompose.screens.jsonfiles.LoadJsonGrammar
import com.example.koreancompose.screens.jsonfiles.LoadJsonWords
import com.example.koreancompose.screens.welcomescreen.WelcomeScreen
import com.example.koreancompose.ui.theme.KoreanComposeTheme
import com.example.koreancompose.viewmodels.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            KoreanComposeTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                LoadJsonWords()
                LoadJsonGrammar()
                Navigation(navController = navController, startDestination = screen)
            }
        }
    }
}









