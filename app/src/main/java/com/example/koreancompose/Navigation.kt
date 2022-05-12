package com.example.koreancompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PracticeScreen.route) {
        composable(route = Screen.PracticeScreen.route) {
            PracticeScreen(navController = navController)
        }
        composable(
            route = Screen.InfoScreen.route +
                    "/{textFieldState}/{word}/{wordDef}/{wordExKor1}/{wordExEng1}/{wordExKor2}/{wordExEng2}/{grammar}/{gramInDepth1}/{inDepth1ExKor}/{inDepth1ExEng}/{gramInDepth2}/{inDepth2ExKor}/{inDepth2ExEng}",
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
                navArgument("wordDef") {
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
                textFieldState = entry.arguments?.getString("textFieldState"),
                word = entry.arguments?.getString("word"),
                wordDef = entry.arguments?.getString("wordDef"),
//                all grammar string
                grammar = entry.arguments?.getString("grammar"),
                gramInDepth1 = entry.arguments?.getString("gramInDepth1"),
                inDepth1ExKor = entry.arguments?.getString("inDepth1ExKor"),
                inDepth1ExEng = entry.arguments?.getString("inDepth1ExEng"),
                gramInDepth2 = entry.arguments?.getString("gramInDepth2"),
                inDepth2ExKor = entry.arguments?.getString("inDepth2ExKor"),
                inDepth2ExEng = entry.arguments?.getString("inDepth2ExEng")

            )
        }
    }
}