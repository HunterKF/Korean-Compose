package com.example.koreancompose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.repository.CardRepository


val viewModel = ViewModel()
var textFieldState = viewModel.textFieldState.value

val TAG = "Button"

val textState = mutableStateOf("")


//Card initializer
val dataWord = CardRepository()
val getAllData = dataWord.getAllData()

@Preview(showSystemUi = true)
@Composable
fun PracticeScreen() {
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
                cardState = cardState
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
        learningPoint(point = viewModel.word)
        Divider(modifier = Modifier
            .height(30.dp)
            .width(2.dp)
        )
        learningPoint(point = viewModel.grammar)
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
    Button(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Blue)
        .height(50.dp),
        onClick = {

            viewModel.sentence = viewModel.textFieldState.value
            viewModel.grammar
            dataWord.allCards.add(PracticeCard(viewModel.word, viewModel.grammar, viewModel.sentence))
            Log.d(TAG, "${dataWord.allCards} AND ${dataWord.getAllData()}")
            onCardItemAdded(viewModel.sentence)

        }
    ) {
        Text("Enter")
    }
}

@Composable
fun DisplayList(modifier: Modifier, cardState: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        items(cardState.size) {}
        items(items = getAllData) { card ->
            CustomItem(practiceCard = card)
            Spacer(Modifier.size(10.dp))
        }

    }
}

@Composable
fun learningPoint(point: String) {
    Row() {
        Text("$point")
        Icon(painter = painterResource(id = R.drawable.ic_arrow_drop_down), "down arrow")
    }
}