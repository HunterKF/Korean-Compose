package com.example.koreancompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.koreancompose.screens.infoscreen.InfoSentence
import com.example.koreancompose.ui.theme.elevation
import com.example.koreancompose.ui.theme.spacing

@Composable
fun InfoScreen(
    textFieldState: String,
    word: String,
    def: String,
    wordExKor1: String,
    wordExEng1: String,
    wordExKor2: String,
    wordExEng2: String,
//    all grammar string
    grammar: String,
    gramInDepth1: String,
    inDepth1ExKor: String,
    inDepth1ExEng: String,
    gramInDepth2: String?,
    inDepth2ExKor: String,
    inDepth2ExEng: String,

    navController: NavHostController
) {

    val scrollState = rememberScrollState()

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        Modifier.background(MaterialTheme.colors.background),
        scaffoldState = scaffoldState,
        topBar = {
            TopBarInfo(
                navController = navController,
            )
        },

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = MaterialTheme.elevation.small,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.secondary)
            ) {
                InfoSentence(textFieldState)
            }

            Column(modifier = Modifier.padding(start = MaterialTheme.spacing.medium)) {
                Text(
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.extraSmall
                    ), style = MaterialTheme.typography.h4, text = "Verb"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = MaterialTheme.elevation.small,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.secondary)
            ) {
                InfoWord(word, def, wordExKor1, wordExEng1, wordExKor2, wordExEng2)
            }
            Column(modifier = Modifier.padding(start = MaterialTheme.spacing.medium)) {
                Text(
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.extraSmall
                    ), style = MaterialTheme.typography.h4, text = "Grammar"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = MaterialTheme.elevation.small,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.secondary)
            ) {
                InfoGrammar(
                    grammar,
                    gramInDepth1,
                    inDepth1ExKor,
                    inDepth1ExEng,
                    gramInDepth2,
                    inDepth2ExKor,
                    inDepth2ExEng
                )
            }

        }
    }

}


