package com.example.koreancompose

sealed class Screen(val route: String) {
    object PracticeScreen : Screen("practice_screen")
    object InfoScreen : Screen("info_screen")
    object FavoritesScreen : Screen("favorites_screen")
    object WordListScreen : Screen("word_list_screen")
    object GrammarListScreen : Screen("grammar_list_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
