package com.example.koreancompose.screens.wordlistscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.*
import com.example.koreancompose.model.ModelJSONWord


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

    val wordListItem = WordListItem(loadWord.dataWords, expandedState = false)

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
                WordListLazyItem(index = index, wordListItem = wordListItem)
            }
        }
    }


}


@Composable
fun WordListLazyItem(
    index: Int,
    wordListItem: WordListItem,
    fontSize: TextUnit = 16.sp
) {

    val isClicked = rememberSaveable { mutableStateOf(wordListItem.expandedState) }
    val indexPlusOne = index + 1
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                isClicked.value = isClicked.value.not()
            })

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
                text = wordListItem.dataWords[index].word
            )
            Text(
                textAlign = TextAlign.Right,
                modifier = Modifier.weight(3f),
                fontSize = fontSize,
                fontWeight = FontWeight.Light,
                text = wordListItem.dataWords[index].def
            )

        }


        if (isClicked.value) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 30.dp)
            ) {
                ExampleW(wordListItem.dataWords[index].wordExKor1)
                ExampleW(wordListItem.dataWords[index].wordExEng1)
                ExampleW(wordListItem.dataWords[index].wordExKor1)
                ExampleW(wordListItem.dataWords[index].wordExEng2)
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
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
//    WordListScreen(wordListItem, allWords)
}