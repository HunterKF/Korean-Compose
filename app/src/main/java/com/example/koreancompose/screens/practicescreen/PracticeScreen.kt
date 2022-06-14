package com.example.koreancompose

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.repository.CardRepository
import com.example.koreancompose.screens.practicescreen.LearningPoint
import com.example.koreancompose.screens.sidedrawerscreen.SideDrawer
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

        var cardState by remember { mutableStateOf(viewModel.itemList) }
        var expandedState by remember { mutableStateOf<PracticeCard?>(null) }
        LazyColumn(state = lazyListState) {
            println("LazyColumn has recomposed!")
            item {
                Column(modifier = Modifier.graphicsLayer {
                    scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                    translationY = scrolledY * 0.1f
                    previousOffset = lazyListState.firstVisibleItemScrollOffset
                }) {
                    LearningPoint(
                        learningPointWord = viewModel.wordFieldState.value,
                        learningPointGrammar = viewModel.grammarFieldState.value
                    )
                    var textFieldHeight by remember { mutableStateOf(250) }

                    TextField(textFieldHeight,
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                when {
                                    focusState.isFocused -> {
                                        textFieldHeight = 100
                                    }
                                    else ->
                                        textFieldHeight = 200
                                }
                            }
                            .fillMaxSize()
                            .focusRequester(focusRequester)
                    )
                }

            }

            item {

            }
            stickyHeader {
                EnterButton(
                    focusRequester = focusRequester,
                    coroutineScope = coroutineScope,
                    lazyListState = lazyListState
                ) { PracticeCard ->
                    cardState = cardState + listOf(PracticeCard)
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(getAllData.reversed()) { card ->
                println("items has fired too!")
                CustomItem(
                    practiceCard = card,
                    navController = navController,
                    expandedState = expandedState == card,
                    onClick = {
                        expandedState = if (expandedState == card) null else card
                    },
                )
                Spacer(Modifier.size(10.dp))
            }

        }
    }

}


@Composable
fun TextField(textFieldHeight: Int, modifier: Modifier) {
    TextField(
        modifier = modifier
            .height(200.dp),
        value = viewModel.textFieldState.value,
        label = {
            Text("Start typing now...")
        },
        onValueChange = { newValue ->
            viewModel.onTextFieldChange(newValue)
        }
    )
}

@Composable
fun EnterButton(
    focusRequester: FocusRequester,
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState,
    onCardItemAdded: (String) -> Unit,

) {


    var text = remember { mutableStateOf("Enter") }
    Button(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Blue)
        .height(50.dp),
        onClick = {

            //Get sentence
            viewModel.sentence = viewModel.textFieldState.value

            when {
                viewModel.sentence === "" && lazyListState.firstVisibleItemIndex == 0 -> {
                    println("1 has fired!")
                    focusRequester.requestFocus()
                }
                viewModel.sentence === "" &&  lazyListState.firstVisibleItemIndex > 0 -> {
                    println("2 has fired!")

                    focusRequester.requestFocus()
                    coroutineScope.launch { lazyListState.animateScrollToItem(0) }
                }
                else -> {
                    println("3 has fired!")

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

                }
            }

//            if (viewModel.sentence === "") {
//                focusRequester.requestFocus()
//            } else {
//
//            }
            viewModel.textFieldState.value = ""
            println("${lazyListState.firstVisibleItemIndex}")

        }
    ) {
        Text(text = if (lazyListState.firstVisibleItemIndex == 0) "Enter" else "Let's Practice!")
    }
}


/*
Scrollbar for the LazyColumn. Taken from StackOverflow -
https://stackoverflow.com/questions/66341823/jetpack-compose-scrollbars?noredirect=1&lq=1
*/
@Composable
fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 4.dp
): Modifier {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration)
    )

    return drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

        // Draw scrollbar if scrolling or if the animation is still running and lazy column has content
        if (needDrawScrollbar && firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
            val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

            drawRect(
                color = Color.Blue,
                topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                alpha = alpha
            )
        }
    }
}




