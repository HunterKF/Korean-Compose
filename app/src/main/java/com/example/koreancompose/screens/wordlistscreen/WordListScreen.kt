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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.koreancompose.*
import com.example.koreancompose.R
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.screens.sidedrawerscreen.SideDrawer


@Composable
fun WordListScreen(navController: NavHostController, focusManager: FocusManager) {
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
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                scope = scope,
                scaffoldState = scaffoldState,
                viewModel = viewModel,
                focusManager = focusManager
            )
        },
        drawerBackgroundColor = colorResource(id = R.color.purple_200),
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
        drawerContent = {
            SideDrawer(
                scaffoldState = scaffoldState,
                navController = navController,
                viewModel = viewModel
            )
        },
    ) {


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
                ExampleW(wordListItem.dataWords[index].wordExKor2)
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