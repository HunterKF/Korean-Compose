package com.example.koreancompose.screens.grammarlistscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.koreancompose.*
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.screens.sidedrawerscreen.SideDrawer
import com.example.koreancompose.ui.theme.PrimaryOrange
import com.example.koreancompose.ui.theme.elevation
import com.example.koreancompose.ui.theme.spacing

@Composable
fun GrammarListScreen(navController: NavController, focusManager: FocusManager) {
    val listState = rememberLazyListState()

    val context = LocalContext.current
    val loadJSON = LoadJSON()
    fun loadGrammar(): ModelJSONGrammar? {
        loadJSON.loadGrammarJson(context)
        return loadJSON.grammarList
    }

    val loadGrammar = loadGrammar()
    val allWords = loadGrammar?.dataGrammar?.size!!

    val grammarListItem = GrammarListItem(loadGrammar.dataGrammar, expandedState = false)


    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                scope = scope,
                scaffoldState = scaffoldState,
                viewModel = com.example.koreancompose.viewModel,
                focusManager = focusManager
            )
        },
        drawerBackgroundColor = PrimaryOrange,
        drawerContent = {
            SideDrawer(
                scaffoldState = scaffoldState,
                navController = navController,
                viewModel = com.example.koreancompose.viewModel
            )
        },
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium),
            state = listState
        ) {
            item {
                Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
            }
            items(allWords) { index ->
                GrammarListLazyItem(index = index, grammarListItem = grammarListItem)
            }
        }
    }
    BackHandler {
        navController.navigate(Screen.PracticeScreen.route)
    }
}


@Composable
fun GrammarListLazyItem(
    index: Int,
    fontSize: TextUnit = 16.sp,
    grammarListItem: GrammarListItem
) {

    val state = remember {
        mutableStateOf(false)
    }

    val isClicked = rememberSaveable { mutableStateOf(grammarListItem.expandedState) }

    val indexPlusOne = index + 1
    Column(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small)
            .shadow(elevation = MaterialTheme.elevation.small, shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colors.secondary)
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
                text = grammarListItem.dataGrammar[index].grammar
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
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                )
            ) {
                ExampleGInfo(
                    grammarListItem.dataGrammar[index].gramInDepth1,
                    grammarListItem.dataGrammar[index].inDepth1ExKor,
                    grammarListItem.dataGrammar[index].inDepth1ExEng
                )
                ExampleGInfo(
                    grammarListItem.dataGrammar[index].gramInDepth2,
                    grammarListItem.dataGrammar[index].inDepth2ExKor,
                    grammarListItem.dataGrammar[index].inDepth2ExEng
                )

                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }

        }
    }

}

@Composable
fun ExampleGInfo(
    grammarExplanation: String,
    learningKoreanPoint: String,
    learningEnglishPoint: String
) {
    if (grammarExplanation != "null") {
        androidx.compose.material3.Text(
            modifier = Modifier.padding(vertical = 2.dp),
            style = MaterialTheme.typography.body2,
            text = grammarExplanation
        )
    }

    Column(
        modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
    ) {
        androidx.compose.material3.Text(
            modifier = Modifier.padding(vertical = 2.dp),
            style = MaterialTheme.typography.h3,
            text = learningKoreanPoint
        )
        androidx.compose.material3.Text(
            modifier = Modifier.padding(vertical = 2.dp),
            style = MaterialTheme.typography.body2,
            text = learningEnglishPoint
        )
    }

}