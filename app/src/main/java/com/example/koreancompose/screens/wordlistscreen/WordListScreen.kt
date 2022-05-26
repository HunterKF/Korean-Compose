package com.example.koreancompose.screens.wordlistscreen

import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.ExampleW
import com.example.koreancompose.LoadJSON
import com.example.koreancompose.Screen
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.model.ModelWord
import com.example.koreancompose.screens.jsonfiles.LoadJsonWords
import com.example.koreancompose.viewModel



@Composable
fun WordListScreen() {
    val listState = rememberLazyListState()

    val context = LocalContext.current
    val loadJSON = LoadJSON()
    fun loadWord(): ModelJSONWord? {
        loadJSON.loadWordJson(context)
        return loadJSON.wordList
    }

    val loadWord = loadWord()
    val allWords = loadWord?.dataWords?.size!!

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.h3.fontSize,
                text = "Words"
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            state = listState
        ) {
            items(allWords) { index ->
                WordListLazyItem(index = index, loadWord = loadWord)
            }
        }
    }


}


@Composable
fun WordListLazyItem(
    index: Int,
    loadWord: ModelJSONWord,
    fontSize: TextUnit = 16.sp
) {

    val state = remember {
        mutableStateOf(false)
    }


    val indexPlusOne = index + 1
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { state.value = state.value === false })

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(0.5f),
                fontSize = fontSize,
                text = "$indexPlusOne"
            )
            Text(
                textAlign = TextAlign.Left,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                text = loadWord.dataWords[index].word
            )
            Text(
                textAlign = TextAlign.Right,
                modifier = Modifier.weight(3f),
                fontSize = fontSize,
                fontWeight = FontWeight.Light,
                text = loadWord.dataWords[index].def
            )

        }
        if (state.value) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 30.dp)
            ) {
                ExampleW(loadWord.dataWords[index].wordExKor1)
                ExampleW(loadWord.dataWords[index].wordExEng1)
                ExampleW(loadWord.dataWords[index].wordExKor1)
                ExampleW(loadWord.dataWords[index].wordExEng2)
                Divider(modifier = Modifier.padding(bottom = 8.dp))
            }

        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }

}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    WordListScreen()
}