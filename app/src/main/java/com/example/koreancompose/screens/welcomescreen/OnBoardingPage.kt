package com.example.koreancompose.screens.welcomescreen

import androidx.annotation.DrawableRes
import com.example.koreancompose.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.ic_arrow_drop_up,
        title = "Practice",
        description = ""
    )
    object Second : OnBoardingPage(
        image = R.drawable.ic_arrow_drop_up,
        title = "Need the keyboard?",
        description = "Installing a Korean keyboard is easy!"
    )
    object Third : OnBoardingPage(
        image = R.drawable.ic_arrow_drop_up,
        title = "Ready to practice?",
        description = "Let's go!"
    )
}
