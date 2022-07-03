package com.example.koreancompose.screens.wordlistscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
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
import com.example.koreancompose.ui.theme.elevation
import com.example.koreancompose.ui.theme.spacing


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
    BackHandler {
        navController.navigate(Screen.PracticeScreen.route)
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
            .padding(vertical = MaterialTheme.spacing.small)
            .shadow(elevation = MaterialTheme.elevation.small, shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .fillMaxWidth()
            .clickable(onClick = {
                isClicked.value = isClicked.value.not()
            })

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                    bottom = if (isClicked.value) 0.dp else MaterialTheme.spacing.medium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                textAlign = TextAlign.Left,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.body2,
                text = wordListItem.dataWords[index].word
            )
            Text(
                textAlign = TextAlign.Right,
                modifier = Modifier.weight(3f),
                style = MaterialTheme.typography.body2,
                text = wordListItem.dataWords[index].def
            )

        }


        if (isClicked.value) {

            Divider(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(0.5.dp),
                color = Color.LightGray
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 30.dp)
            ) {

                ExampleKorW(wordListItem.dataWords[index].wordExKor1)
                ExampleEngW(wordListItem.dataWords[index].wordExEng1)
                ExampleKorW(wordListItem.dataWords[index].wordExKor2)
                ExampleEngW(wordListItem.dataWords[index].wordExEng2)
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