package com.example.koreancompose

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.koreancompose.screens.favoritesscreen.FavoritesScreen
import com.example.koreancompose.screens.grammarlistscreen.GrammarListScreen
import com.example.koreancompose.screens.welcomescreen.InstallKeyboardScreen
import com.example.koreancompose.screens.welcomescreen.WelcomeScreen
import com.example.koreancompose.screens.wordlistscreen.WordListScreen

@Composable
fun Navigation(navController: NavHostController, startDestination: String) {


    val focusManager = LocalFocusManager.current

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.PracticeScreen.route) {
            PracticeScreen(navController = navController, focusManager = focusManager)
        }
        composable(
            route = Screen.InfoScreen.route +
                    "/{textFieldState}/{word}/{def}/{wordExKor1}/{wordExEng1}/{wordExKor2}/{wordExEng2}/{grammar}/{gramInDepth1}/{inDepth1ExKor}/{inDepth1ExEng}/{gramInDepth2}/{inDepth2ExKor}/{inDepth2ExEng}",
            arguments = listOf(
                /*Inputted sentence*/
                navArgument("textFieldState") {
                    type = NavType.StringType
                    defaultValue = "Did you enter something in?"
                },
                /*Words*/
                navArgument("word") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("def") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("wordExKor1") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("wordExEng1") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("wordExKor2") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("wordExEng2") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                /*Grammar*/
                navArgument("grammar") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("gramInDepth1") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("inDepth1ExKor") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("inDepth1ExEng") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("gramInDepth2") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("inDepth2ExKor") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("inDepth2ExEng") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                }
            )
        ) { entry ->
            InfoScreen(
                textFieldState = entry.arguments!!.getString("textFieldState").toString(),
                word = entry.arguments!!.getString("word").toString(),
                def = entry.arguments!!.getString("def").toString(),
                wordExKor1 = entry.arguments!!.getString("wordExKor1").toString(),
                wordExEng1 = entry.arguments!!.getString("wordExEng1").toString(),
                wordExKor2 = entry.arguments!!.getString("wordExKor2").toString(),
                wordExEng2 = entry.arguments!!.getString("wordExEng2").toString(),

//                all grammar string
                grammar = entry.arguments!!.getString("grammar").toString(),
                gramInDepth1 = entry.arguments!!.getString("gramInDepth1").toString(),
                inDepth1ExKor = entry.arguments!!.getString("inDepth1ExKor").toString(),
                inDepth1ExEng = entry.arguments!!.getString("inDepth1ExEng").toString(),
                gramInDepth2 = entry.arguments?.getString("gramInDepth2").toString(),
                inDepth2ExKor = entry.arguments!!.getString("inDepth2ExKor").toString(),
                inDepth2ExEng = entry.arguments!!.getString("inDepth2ExEng").toString(),
                navController = navController,
                focusManager = focusManager


            )
        }
        composable(route = Screen.FavoritesScreen.route) {
            FavoritesScreen(navController = navController, focusManager = focusManager)
        }
        composable(
            route = Screen.WordListScreen.route
        ) {
            WordListScreen(navController = navController, focusManager = focusManager)
        }
        composable(
            route = Screen.GrammarListScreen.route
        ) {
            GrammarListScreen(navController = navController, focusManager = focusManager)
        }
        composable(
            route = Screen.InstallKeyboardScreen.route
        ) {
            InstallKeyboardScreen()
        }
    }
}