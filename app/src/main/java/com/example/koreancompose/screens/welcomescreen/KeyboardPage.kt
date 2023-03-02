package com.example.koreancompose.screens.welcomescreen

import androidx.annotation.DrawableRes
import com.example.koreancompose.R

sealed class KeyboardPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
    val buttonText: String
) {
    object Gboard : KeyboardPage (
        image = R.drawable.google_gboard_logo,
        title = "Need gboard?",
        description = "A great keyboard for Korean is gboard! Install gboard and follow its instructions to enable it for your phone.",
        buttonText = "Install Gboard"
    )
    object CheckKeyboard : KeyboardPage (
        image = R.drawable.korean_keyboard,
        title = "Add the language!",
        description = "Go to Settings > General Management > Gboard settings > Languages. Click 'Add Keyboard' and choose the Korean keyboard.",
        buttonText = "Add Keyboard"
    )
}
