package com.example.koreancompose

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.repository.CardRepository
import com.example.koreancompose.screens.practicescreen.LearningPoint
import com.example.koreancompose.screens.sidedrawerscreen.SideDrawer
import com.example.koreancompose.ui.theme.Shapes
import com.example.koreancompose.ui.theme.elevation
import com.example.koreancompose.ui.theme.spacing
import com.example.koreancompose.viewmodels.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


val viewModel = ViewModel()

val TAG = "randomNumber"

//Card initializer
val cardRepository = CardRepository()
val getAllData = cardRepository.getAllData()


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PracticeScreen(navController: NavController, focusManager: FocusManager) {

    val activity = (LocalContext.current as? Activity)
    BackHandler() {
        activity?.finish()
    }
    //Focus request for Button to force focus to text field
    val focusRequester = FocusRequester()
//    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    //Remove column's clickable effects
    val interactionSource = remember { MutableInteractionSource() }
    //lazyColumn state
    val listState = rememberLazyListState()
    //coroutineScope
    val coroutineScope = rememberCoroutineScope()
    //For the scaffold
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = viewModel.topBarText) {
        viewModel.topBarText.value = "Practice"
    }
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0

    //Detect keybaord
    val isVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    Scaffold(
        Modifier.background(MaterialTheme.colors.background),
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                scope = scope,
                scaffoldState = scaffoldState,
                viewModel = viewModel,
                focusManager = focusManager
            )
        },
        drawerBackgroundColor = MaterialTheme.colors.primaryVariant,
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
        drawerContent = {
            SideDrawer(
                scaffoldState = scaffoldState,
                navController = navController,
                viewModel = viewModel
            )
        },
    ) {

        var cardState by remember { mutableStateOf(viewModel.itemList) }
        var expandedState by remember { mutableStateOf<PracticeCard?>(null) }
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium
            )
        ) {

            if (lazyListState.isScrollInProgress && lazyListState.firstVisibleItemIndex != 0) {
                focusManager.clearFocus()
            }
            item {
                Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
            }
            item {
                Column(modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small)
                    .shadow(
                        elevation = MaterialTheme.elevation.small,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(color = Color.White)
                    .graphicsLayer {
                        scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                        translationY = scrolledY * 0.0f
                        previousOffset = lazyListState.firstVisibleItemScrollOffset
                    }) {
                    LearningPoint(
                        learningPointWord = viewModel.wordFieldState.value,
                        learningPointGrammar = viewModel.grammarFieldState.value,
                        onClick = {
                            if (viewModel.textFieldHeight.value == 100) {
                                focusRequester.requestFocus()
                                println("The if statement has fired! The keyboard is now $isVisible")
                            }

                        }
                    )


                }

            }

            item {
                TextField(
                    viewModel.textFieldHeight.value,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.medium)
                        .shadow(
                            elevation = MaterialTheme.elevation.small,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .onFocusChanged { focusState ->
                            when {
                                focusState.isFocused -> {
                                    viewModel.textFieldHeight.value = 100
                                }
                                else -> {
                                    viewModel.textFieldHeight.value = 200
                                }

                            }
                        }
                        .fillMaxSize()
                        .focusRequester(focusRequester)
                )
            }
            stickyHeader {
                EnterButton(
                    modifier = Modifier,
                    focusRequester = focusRequester,
                    coroutineScope = coroutineScope,
                    lazyListState = lazyListState
                ) { PracticeCard ->
                    cardState = cardState + listOf(PracticeCard)
                }
            }

            items(getAllData.reversed()) { card ->
                CustomItem(
                    modifier = Modifier,
                    practiceCard = card,
                    navController = navController,
                    focusManager = focusManager,
                    expandedState = expandedState == card,
                    onClick = {
                        expandedState = if (expandedState == card) null else card
                        focusManager.clearFocus()
                    },
                )
            }

        }
    }

}


@Composable
fun TextField(textFieldHeight: Int, modifier: Modifier) {
    TextField(
        modifier = modifier
            .height(textFieldHeight.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        value = viewModel.textFieldState.value,
        label = {
            Text(if (textFieldHeight == 200) stringResource(R.string.app_name) else "")
        },
        onValueChange = { newValue ->
            viewModel.onTextFieldChange(newValue)
        }
    )
}

@Composable
fun EnterButton(
    modifier: Modifier,
    focusRequester: FocusRequester,
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState,
    onCardItemAdded: (String) -> Unit,

    ) {
    Button(modifier = Modifier
        .padding(vertical = if (lazyListState.firstVisibleItemIndex === 0 || lazyListState.firstVisibleItemIndex === 1) MaterialTheme.spacing.small else 0.dp)
        .shadow(
            elevation = MaterialTheme.elevation.small,
            shape = if (lazyListState.firstVisibleItemIndex === 0 || lazyListState.firstVisibleItemIndex === 1) RoundedCornerShape(
                10.dp
            ) else RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 10.dp,
                bottomStart = 10.dp
            )
        )
//        .clip(shape = )
        .fillMaxWidth()
        .height(50.dp),
        onClick = {

            //Get sentence
            viewModel.sentence = viewModel.textFieldState.value

            when {
                viewModel.sentence === "" && lazyListState.firstVisibleItemIndex == 0 -> {
                    focusRequester.requestFocus()

                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                }
                viewModel.sentence === "" && lazyListState.firstVisibleItemIndex > 0 -> {
                    focusRequester.requestFocus()
                    coroutineScope.launch { lazyListState.animateScrollToItem(0) }
                }
                viewModel.sentence != "" && lazyListState.firstVisibleItemIndex > 0 -> {
                    focusRequester.requestFocus()
                    coroutineScope.launch { lazyListState.animateScrollToItem(0) }
                }
                else -> {
                    cardRepository.allCards.add(
                        PracticeCard(
                            viewModel.sentence,
                            viewModel.wordFieldState.value,
                            viewModel.wordDefFieldState.value,
                            viewModel.wordExKor1.value,
                            viewModel.wordExEng1.value,
                            viewModel.wordExKor2.value,
                            viewModel.wordExEng2.value,
                            viewModel.grammarFieldState.value,
                            viewModel.grammarInD1State.value,
                            viewModel.grammarInD1ExKor.value,
                            viewModel.grammarInD1ExEng.value,
                            viewModel.grammarInD2State.value,
                            viewModel.grammarInD2ExKor.value,
                            viewModel.grammarInD2ExEng.value,
                            isClicked = mutableStateOf(false)
                        )
                    )
                    onCardItemAdded(viewModel.sentence)


                    //Change word

                    val randWord = viewModel.allWords.let { viewModel.returnWord(it) }
                    //Word - Takes the data class from returnGrammar and then populates the states in the viewModel.
                    viewModel.wordFieldState.value = randWord.word
                    viewModel.wordDefFieldState.value = randWord.def
                    viewModel.wordExKor1.value = randWord.wordExKor1
                    viewModel.wordExEng1.value = randWord.wordExEng1
                    viewModel.wordExKor2.value = randWord.wordExKor2
                    viewModel.wordExEng2.value = randWord.wordExEng2


                    //Change grammar

                    val randGrammar = viewModel.allGrammar.let { viewModel.returnGrammar(it) }
                    //Grammar - Takes the data class from returnGrammar and then populates the states in the viewModel.
                    viewModel.grammarFieldState.value = randGrammar.grammar
                    viewModel.grammarInD1State.value = randGrammar.gramInDepth1
                    viewModel.grammarInD1ExKor.value = randGrammar.inDepth1ExKor
                    viewModel.grammarInD1ExEng.value = randGrammar.inDepth1ExEng
                    viewModel.grammarInD2State.value = randGrammar.gramInDepth2
                    viewModel.grammarInD2ExKor.value = randGrammar.inDepth2ExKor
                    viewModel.grammarInD2ExEng.value = randGrammar.inDepth2ExEng

                    viewModel.textFieldState.value = ""
                }
            }

        }
    ) {
        Text(text = if (lazyListState.firstVisibleItemIndex === 0 || lazyListState.firstVisibleItemIndex === 1) "Save" else "Back to top")
    }
}

