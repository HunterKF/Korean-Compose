package com.example.koreancompose.screens.practicescreen.CustomItemFuns

import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.navigation.NavController
import com.example.koreancompose.Screen
import com.example.koreancompose.model.PracticeCard

@Composable
fun InfoFun(practiceCard: PracticeCard, navController: NavController) {
    IconButton(
        modifier = Modifier
            .alpha(ContentAlpha.medium),
        onClick = {
            navController.navigate(
                Screen.InfoScreen.withArgs(
                    practiceCard.inputtedSentence,
                    practiceCard.word,
                    practiceCard.wordDef,
                    practiceCard.wordExampleKorean1,
                    practiceCard.wordExampleEnglish1,
                    practiceCard.wordExampleKorean2,
                    practiceCard.wordExampleEnglish2,
                    practiceCard.grammar,
                    practiceCard.gramInDepth1,
                    practiceCard.inDepth1ExKor,
                    practiceCard.inDepth1ExEng,
                    practiceCard.gramInDepth2,
                    practiceCard.inDepth2ExKor,
                    practiceCard.inDepth2ExEng
                )
            )
        }) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Info"
        )
    }
}