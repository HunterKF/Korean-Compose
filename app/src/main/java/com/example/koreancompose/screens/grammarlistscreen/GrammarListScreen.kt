package com.example.koreancompose.screens.grammarlistscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.ExampleG
import com.example.koreancompose.ExampleW
import com.example.koreancompose.LoadJSON
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.screens.wordlistscreen.WordListLazyItem

@Composable
fun GrammarListScreen() {
    val listState = rememberLazyListState()

    val context = LocalContext.current
    val loadJSON = LoadJSON()
    fun loadGrammar(): ModelJSONGrammar? {
        loadJSON.loadGrammarJson(context)
        return loadJSON.grammarList
    }

    val loadGrammar = loadGrammar()
    val allWords = loadGrammar?.dataGrammar?.size!!



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
                text = "Grammar"
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            state = listState
        ) {
            items(allWords) { index ->
                GrammarListLazyItem(index = index, loadGrammar = loadGrammar)
            }
        }
    }

}

@Composable
fun GrammarListLazyItem(
    index: Int,
    loadGrammar: ModelJSONGrammar,
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
                text = loadGrammar.dataGrammar[index].grammar
            )


        }
        if (state.value) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 30.dp)
            ) {
                ExampleG(loadGrammar.dataGrammar[index].gramInDepth1, loadGrammar.dataGrammar[index].inDepth1ExKor, loadGrammar.dataGrammar[index].inDepth1ExEng )
                ExampleG(loadGrammar.dataGrammar[index].gramInDepth2, loadGrammar.dataGrammar[index].inDepth2ExKor, loadGrammar.dataGrammar[index].inDepth2ExEng )

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