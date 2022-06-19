package com.example.koreancompose.screens.welcomescreen

import androidx.annotation.DrawableRes
import com.example.koreancompose.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
//    <a href='https://www.freepik.com/vectors/book-online'>Book online vector created by pch.vector - www.freepik.com</a>
    object First : OnBoardingPage(
        image = R.drawable.rsz_study_icon_2,
        title = "Practice",
        description = ""
    )
//    <a href='https://www.freepik.com/vectors/illustration-icon'>Illustration icon vector created by rawpixel.com - www.freepik.com</a>
    object Second : OnBoardingPage(
        image = R.drawable.rsz_25567,
        title = "Need the keyboard?",
        description = "Installing a Korean keyboard is easy!"
    )
    object Third : OnBoardingPage(
        image = R.drawable.rsz_man_woman_hiking_illustration_74855_18410,
        title = "Ready to practice?",
        description = "Let's go!"
    )
}
