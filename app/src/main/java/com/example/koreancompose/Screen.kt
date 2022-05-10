package com.example.koreancompose

sealed class Screen(val route: String) {
    object PracticeScreen : Screen("practice_screen")
    object InfoScreen : Screen("info_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
