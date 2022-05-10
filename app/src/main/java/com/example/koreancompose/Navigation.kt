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
            route = Screen.InfoScreen.route + "/{textFieldState}",
            arguments = listOf(
                navArgument("textFieldState") {
                    type = NavType.StringType
                    defaultValue = "Did you enter something in?"
                }
            )
        ) { entry ->
            InfoScreen(textFieldState = entry.arguments?.getString("textFieldState"))
        }
    }
}