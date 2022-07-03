package com.example.koreancompose.screens.practicescreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koreancompose.LearningPointGrammar
import com.example.koreancompose.LearningPointWord
import com.example.koreancompose.ui.theme.spacing
import com.example.koreancompose.viewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LearningPoint(
    learningPointWord: String?,
    learningPointGrammar: String?,
    onClick: () -> kotlin.Unit
) {
    var expandedWordState by remember { mutableStateOf(false) }
    var expandedGrammarState by remember { mutableStateOf(false) }
    val rotateStateWord by animateFloatAsState(targetValue = if (expandedWordState) 180f else 0f)
    val rotateStateGrammar by animateFloatAsState(targetValue = if (expandedGrammarState) 180f else 0f)


    Column{
        Row(
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Word row
            Row(
                modifier = Modifier.clickable {

                    if (expandedGrammarState) {
                        expandedGrammarState = false

                    }
                    expandedWordState = !expandedWordState
                    onClick


                },

                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                if (learningPointWord != null) {
                    LearningPoint(text = learningPointWord)
                }

                Icon(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotateStateWord),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop"
                )
            }

            Divider(
                modifier = Modifier
                    .height(30.dp)
                    .width(2.dp)
            )

            //Grammar row
            Row(
                modifier = Modifier.clickable {
                    if (expandedWordState) {
                        expandedWordState = false
                    }
                    expandedGrammarState = !expandedGrammarState
                    onClick
                },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (learningPointGrammar != null) {
                    LearningPoint(text = learningPointGrammar)
                }

                Icon(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotateStateGrammar),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop"
                )
            }
        }
    }
    when {
        expandedGrammarState ->
            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable {
                    if (expandedWordState) {
                        expandedWordState = false
                    }
                    expandedGrammarState = !expandedGrammarState
                }) {
                LearningPointGrammar(
                    grammar = viewModel.grammarFieldState.value,
                    gramInDepth1 = viewModel.grammarInD1State.value,
                    inDepth1ExKor = viewModel.grammarInD1ExKor.value,
                    inDepth1ExEng = viewModel.grammarInD1ExEng.value,
                    gramInDepth2 = viewModel.grammarInD2State.value,
                    inDepth2ExKor = viewModel.grammarInD2ExKor.value,
                    inDepth2ExEng = viewModel.grammarInD2ExEng.value
                )
            }

        expandedWordState ->

            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable {
                    if (expandedGrammarState) {
                        expandedGrammarState = false
                    }
                    expandedWordState = !expandedWordState
                }) {
                LearningPointWord(
                    word = viewModel.wordFieldState.value,
                    def = viewModel.wordDefFieldState.value,
                    wordExKor1 = viewModel.wordExKor1.value,
                    wordExEng1 = viewModel.wordExEng1.value,
                    wordExKor2 = viewModel.wordExKor2.value,
                    wordExEng2 = viewModel.wordExEng2.value
                )
            }
    }
}

@Composable
fun LearningPoint(text: String?) {
    if (text != null) {
        Text(text)
    }
}
