package com.example.koreancompose.screens.welcomescreen

import androidx.annotation.DrawableRes
import com.jagerapps.koreancompose.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
//    <a href='https://www.freepik.com/vectors/book-online'>Book online vector created by pch.vector - www.freepik.com</a>
    object First : OnBoardingPage(
        image = R.drawable.onboarding_welcome,
        title = "Welcome",
        description = "Korean Compose encourages you to use verb endings with a variety of different verbs."
    )
//    <a href='https://www.freepik.com/vectors/illustration-icon'>Illustration icon vector created by rawpixel.com - www.freepik.com</a>
    object Second : OnBoardingPage(
        image = R.drawable.onboarding_keyboard,
        title = "Need the keyboard?",
        description = "If you don't have the Korean keyboard yet, you can follow these simple steps get one!"
    )
    object Third : OnBoardingPage(
        image = R.drawable.onboarding_start,
        title = "Ready to practice?",
        description = "You're all set and ready to go! Enjoy practicing your Korean skills! "
    )
}
