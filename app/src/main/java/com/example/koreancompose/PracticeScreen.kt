package com.example.koreancompose

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
import com.example.koreancompose.repository.data_word


val viewModel = MyViewModel()
var textFieldState = viewModel.textFieldState.value

val TAG = "TextField"

val textState = mutableStateOf("")


//Card initializer
val dataWord = data_word()
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
        learningPoint(point = "가다")
        Divider(modifier = Modifier
            .height(30.dp)
            .width(2.dp)
        )
        learningPoint(point = "으려고")
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
            onCardItemAdded(viewModel.textFieldState.value)

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

        items(cardState.size) { index ->
            Text(text = cardState[index])
        }
        items(items = getAllData) { card ->
            CustomItem(practiceCard = card)
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