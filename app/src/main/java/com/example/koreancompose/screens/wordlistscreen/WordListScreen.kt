package com.example.koreancompose.screens.wordlistscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.LoadJSON
import com.example.koreancompose.Screen
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.model.ModelWord
import com.example.koreancompose.screens.jsonfiles.LoadJsonWords
import com.example.koreancompose.viewModel


lateinit var allWords: ModelJSONWord
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

    Text("Words")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        state = listState) {
        items(allWords) { index ->
            WordListLazyItem(index = index, loadWord = loadWord)
        }
    }
}


@Composable
fun WordListLazyItem(index: Int, loadWord: ModelJSONWord) {
    val expandedState = false
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = { !expandedState  } )
        .padding(16.dp)

            ) {
        Row(

        ) {
            Text("$index")
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "${loadWord.dataWords[index].word}"
            )

        }
        if (expandedState) {
            Text("Hello")
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    WordListScreen()
}