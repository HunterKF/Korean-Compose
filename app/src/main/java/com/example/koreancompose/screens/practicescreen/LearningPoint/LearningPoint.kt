package com.example.koreancompose.screens.practicescreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koreancompose.*
import com.example.koreancompose.R
import com.example.koreancompose.ui.theme.PrimaryOrange
import com.example.koreancompose.ui.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LearningPoint(
    learningPointWord: String?,
    learningPointGrammar: String?,
    focusManager: FocusManager,
    onClick: () -> Unit
) {
    var expandedWordState by remember { mutableStateOf(false) }
    var expandedGrammarState by remember { mutableStateOf(false) }
    val rotateStateWord by animateFloatAsState(targetValue = if (expandedWordState) 180f else 0f)
    val rotateStateGrammar by animateFloatAsState(targetValue = if (expandedGrammarState) 180f else 0f)
    val colorWord = if (expandedWordState) PrimaryOrange else Color.Gray
    val colorGrammar = if (expandedGrammarState) PrimaryOrange else Color.Gray

    Column {
        Row(
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Word row
            Row(
                modifier = Modifier
                    .weight(2f)
                    .clickable {

                        if (expandedGrammarState) {
                            expandedGrammarState = false
                        }
                        focusManager.clearFocus()
                        expandedWordState = !expandedWordState
                    },

                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {

                LearningPoint(text = learningPointWord)


                Icon(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotateStateWord)
                        .scale(0.8f)
                        .padding(start = MaterialTheme.spacing.extraSmall),
                    tint = colorWord,
                    painter = painterResource(id = R.drawable.ic_round_expand_more_24),
                    contentDescription = "Drop"
                )
            }

            IconButton(modifier = Modifier.weight(1f), onClick = {
                //Change word

                val randWord = viewModel.allWords.let { viewModel.returnWord(it) }
                //Word - Takes the data class from returnGrammar and then populates the states in the viewModel.
                viewModel.wordFieldState.value = randWord.word
                viewModel.wordDefFieldState.value = randWord.def
                viewModel.wordExKor1.value = randWord.wordExKor1
                viewModel.wordExEng1.value = randWord.wordExEng1
                viewModel.wordExKor2.value = randWord.wordExKor2
                viewModel.wordExEng2.value = randWord.wordExEng2


                //Change grammar

                val randGrammar = viewModel.allGrammar.let { viewModel.returnGrammar(it) }
                //Grammar - Takes the data class from returnGrammar and then populates the states in the viewModel.
                viewModel.grammarFieldState.value = randGrammar.grammar
                viewModel.grammarInD1State.value = randGrammar.gramInDepth1
                viewModel.grammarInD1ExKor.value = randGrammar.inDepth1ExKor
                viewModel.grammarInD1ExEng.value = randGrammar.inDepth1ExEng
                viewModel.grammarInD2State.value = randGrammar.gramInDepth2
                viewModel.grammarInD2ExKor.value = randGrammar.inDepth2ExKor
                viewModel.grammarInD2ExEng.value = randGrammar.inDepth2ExEng
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_loop_24),
                    tint = PrimaryOrange,
                    contentDescription = ""
                )
            }


            //Grammar row
            Row(
                modifier = Modifier
                    .weight(2f)
                    .clickable {
                        if (expandedWordState) {
                            expandedWordState = false
                        }
                        focusManager.clearFocus()
                        expandedGrammarState = !expandedGrammarState
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                LearningPoint(text = learningPointGrammar)


                Icon(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotateStateGrammar)
                        .scale(0.8f)
                        .padding(start = MaterialTheme.spacing.extraSmall),
                    tint = colorGrammar,
                    painter = painterResource(id = R.drawable.ic_round_expand_more_24),
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
                    onClick
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
                    onClick
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
