package com.example.koreancompose.screens.welcomescreen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.example.koreancompose.R
import dagger.hilt.android.qualifiers.ApplicationContext

sealed class KeyboardPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object Gboard : KeyboardPage (
        image = R.drawable.google_gboard_logo,
        title = "Need gboard?",
        description = "A great keyboard for Korean is gboard! Install gboard and follow its instructions to enable it for your phone."
    )
    object CheckKeyboard : KeyboardPage (
        image = R.drawable.google_gboard_logo,
        title = "Add the language!",
        description = "You can add a keyboard simply. Go to Settings > General Management > Gboard settings > Languages. Click 'Add Keyboard' and choose the Korean keyboard. You can choose a keyboard you like, the most common one is 2-Bulsik."
    )
}
