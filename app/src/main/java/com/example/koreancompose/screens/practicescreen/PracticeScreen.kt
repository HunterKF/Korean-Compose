package com.example.koreancompose

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.repository.CardRepository
import com.example.koreancompose.screens.practicescreen.LearningPoint


val viewModel = ViewModel()

val TAG = "randomNumber"

//Card initializer
val cardRepository = CardRepository()
val getAllData = cardRepository.getAllData()


@Composable
fun PracticeScreen(navController: NavController) {

    //Focus request for Button to force focus to text field
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    //Remove column's clickable effects
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { focusManager.clearFocus() },
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        var cardState by remember { mutableStateOf(viewModel.itemList) }
        LearningPoint(
            learningPointWord = viewModel.wordFieldState.value,
            learningPointGrammar = viewModel.grammarFieldState.value
        )

        var textFieldHeight by remember { mutableStateOf(250) }

        TextField(textFieldHeight,
            modifier = Modifier
                .onFocusChanged { focusState ->
                    when {
                        focusState.isFocused -> {
                            textFieldHeight = 100
                            println("I have focus!")
                            println(" The current value of textFieldHeight is $textFieldHeight")
                        }
                        else ->
                            textFieldHeight = 200
                    }
                }
                .fillMaxSize()
                .focusRequester(focusRequester)
        )

        Button(focusRequester = focusRequester) { PracticeCard ->
            cardState = cardState + listOf(PracticeCard)
        }
        Column(modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { focusManager.clearFocus() }
            .weight(2f)) {
            DisplayList(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
                cardState = cardState,
                navController = navController
            )
        }
    }
}


@Composable
fun TextField(textFieldHeight: Int, modifier: Modifier) {
    TextField(
        modifier = modifier
            .height(textFieldHeight.dp),
        value = viewModel.textFieldState.value,
        label = {
            Text("Start typing now...")
        },
        onValueChange = { newValue ->
            viewModel.onTextFieldChange(newValue)
        }
    )
}

@Composable
fun Button(
    focusRequester: FocusRequester,
    onCardItemAdded: (String) -> Unit
) {
    Button(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Blue)
        .height(50.dp),
        onClick = {

            //Get sentence
            viewModel.sentence = viewModel.textFieldState.value
            if (viewModel.sentence === "") {
                focusRequester.requestFocus()

            } else {
                Log.d("onClick", "Before add card")
                cardRepository.allCards.add(
                    PracticeCard(
                        viewModel.sentence,
                        viewModel.wordFieldState.value,
                        viewModel.wordDefFieldState.value,
                        viewModel.wordExKor1.value,
                        viewModel.wordExEng1.value,
                        viewModel.wordExKor2.value,
                        viewModel.wordExEng2.value,
                        viewModel.grammarFieldState.value,
                        viewModel.grammarInD1State.value,
                        viewModel.grammarInD1ExKor.value,
                        viewModel.grammarInD1ExEng.value,
                        viewModel.grammarInD2State.value,
                        viewModel.grammarInD2ExKor.value,
                        viewModel.grammarInD2ExEng.value,
                    )
                )

                Log.d("onClick", "Does it die here? Here the value of practice card is ${cardRepository.allCards} ")
                onCardItemAdded(viewModel.sentence)


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

            }
            viewModel.textFieldState.value = ""


        }
    ) {
        Text("Enter")
    }
}

@Composable
fun DisplayList(modifier: Modifier, cardState: List<String>, navController: NavController) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        reverseLayout = true,
        verticalArrangement = Arrangement.Top,
        userScrollEnabled = true,
        state = listState
    ) {
        if (listState.isScrollInProgress) {
            println("The list state is scrolling")
        }
        items(cardState.size) {}
        items(items = getAllData) { card ->
            CustomItem(practiceCard = card, navController = navController)
            Spacer(Modifier.size(10.dp))

        }


    }
}


