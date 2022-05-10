package com.example.koreancompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.repository.CardRepository


val viewModel = ViewModel()

val TAG = "LearningBar"

val textState = mutableStateOf("")


//Card initializer
val dataWord = CardRepository()
val getAllData = dataWord.getAllData()

//Open LoadJSON
val loadJson = LoadJSON()


@Composable
fun PracticeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        var cardState by remember { mutableStateOf(viewModel.itemList)}

        LearningBar()
        Column(modifier = Modifier
            .weight(1f)) {
            TextField(modifier = Modifier
                .background(color = Color.Red))
        }
        Button { PracticeCard ->
            cardState = cardState + listOf(PracticeCard)
        }
        Column(modifier = Modifier.weight(2f)) {
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
fun LearningBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        learningPoint(point = viewModel.wordFieldState.value)
        Divider(modifier = Modifier
            .height(30.dp)
            .width(2.dp)
        )
        learningPoint(point = viewModel.grammarFieldState.value)
    }
}

@Composable
fun TextField(modifier: Modifier) {
    TextField(
        value = viewModel.textFieldState.value,
        label = {
            Text("Start typing now...")
        },
        onValueChange = { newValue ->
            viewModel.onTextFieldChange(newValue)
        },
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun Button(
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
            dataWord.allCards.add(PracticeCard(
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
            val loadJSON = LoadJSON()
            fun loadWord(): ModelJSONWord? {
                loadJSON.loadWordJson(context)
                return loadJSON.wordList
            }

            val allWords = loadWord()
            val randWord = allWords?.let { viewModel.returnWord(it) }

            viewModel.wordFieldState.value = randWord?.word.toString()
            viewModel.wordDefFieldState.value = randWord?.def.toString()

            //Change grammar
            fun loadGrammar(): ModelJSONGrammar? {
                loadJSON.loadGrammarJson(context)
                return loadJSON.grammarList
            }
            val allGrammar = loadGrammar()
            val randGrammar = allGrammar?.let { viewModel.returnGrammar(it) }

            viewModel.grammarFieldState.value = randGrammar?.grammar.toString()
            viewModel.grammarDefFieldState.value = randGrammar?.def.toString()
            viewModel.grammarExampleFieldState.value = randGrammar?.exampleSentence.toString()



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

@Composable
fun learningPoint(point: String) {
    Row() {
        Text(point)
        Icon(painter = painterResource(id = R.drawable.ic_arrow_drop_down), "down arrow")
    }
}

//@Composable
//fun testingJson() {
//
//    /*This constantly refreshes, so it will always change.
//    * Have to move this function into Button(onClick)...*/
//    val context = LocalContext.current
//    val loadWords = LoadJSON()
//    Log.d(TAG, "Do we die here?")
//    fun loadWord(): ModelJSONWord? {
//        loadWords.loadWordJson(context)
//        val word = loadWords.wordList
//        Log.d(TAG, "Random grammar is $word")
//        return word
//    }
//    Log.d(TAG, "Or do we die here?")
//
//    val allWords = loadWord()
//    val randWord = allWords?.let { viewModel.returnWord(it) }
//
//    viewModel.wordFieldState.value = randWord?.word.toString()
//    Text(text = "${viewModel.wordFieldState.value}")
//}

