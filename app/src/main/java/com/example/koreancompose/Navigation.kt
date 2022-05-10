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
            route = Screen.InfoScreen.route + "/{textFieldState}/{word}/{wordDef}/{grammar}/{grammarDef}/{grammarExample}",
            arguments = listOf(
                navArgument("textFieldState") {
                    type = NavType.StringType
                    defaultValue = "Did you enter something in?"
                },
                navArgument("word") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("wordDef") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("grammar") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("grammarDef") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                },
                navArgument("grammarExample") {
                    type = NavType.StringType
                    defaultValue = "이건 안 했어?"
                }
            )
        ) { entry ->
            InfoScreen(
                textFieldState = entry.arguments?.getString("textFieldState"),
                word = entry.arguments?.getString("word"),
                wordDef = entry.arguments?.getString("wordDef"),
                grammar = entry.arguments?.getString("grammar"),
                grammarDef = entry.arguments?.getString("grammarDef"),
                grammarExample = entry.arguments?.getString("grammarExample")
            )
        }
    }
}