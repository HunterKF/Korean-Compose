package com.example.koreancompose

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.repository.CardRepository
import com.example.koreancompose.screens.practicescreen.LearningPoint


val viewModel = ViewModel()

val TAG = "LearningBar"

//Card initializer
val dataWord = CardRepository()
val getAllData = dataWord.getAllData()


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

    val context = LocalContext.current
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
                dataWord.allCards.add(
                    PracticeCard(
                        viewModel.wordFieldState.value,
                        viewModel.wordDefFieldState.value,
                        viewModel.grammarFieldState.value,
                        viewModel.grammarDefFieldState.value,
                        viewModel.grammarExampleFieldState.value,
                        viewModel.sentence
                    )
                )
                onCardItemAdded(viewModel.sentence)

                //Change word

                val randWord = viewModel.allWords.let { viewModel.returnWord(it) }

                viewModel.wordFieldState.value = randWord?.word.toString()
                viewModel.wordDefFieldState.value = randWord?.def.toString()

                //Change grammar

                val randGrammar = viewModel.allGrammar.let { viewModel.returnGrammar(it) }

                viewModel.grammarFieldState.value = randGrammar?.grammar.toString()
                viewModel.grammarDefFieldState.value = randGrammar?.def.toString()
                viewModel.grammarExampleFieldState.value = randGrammar?.exampleSentence.toString()

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


        items(cardState.size) {}
        items(items = getAllData) { card ->
            CustomItem(practiceCard = card, navController = navController)
            Spacer(Modifier.size(10.dp))
        }

    }
}


