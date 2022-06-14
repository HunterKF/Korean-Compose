//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.material.DrawerValue
//import androidx.compose.material.Scaffold
//import androidx.compose.material.rememberDrawerState
//import androidx.compose.material.rememberScaffoldState
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.focus.onFocusChanged
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.unit.dp
//import com.example.koreancompose.*
//import com.example.koreancompose.R
//import com.example.koreancompose.screens.practicescreen.LearningPoint
//import com.example.koreancompose.screens.sidedrawerscreen.SideDrawer
//
//@Composable
//fun TestLazyColumn() {
//
//    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
//    val scope = rememberCoroutineScope()
//
//    val lazyListState = rememberLazyListState()
//    var scrolledY = 0f
//    var previousOffset = 0
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopBar(
//                scope = scope,
//                scaffoldState = scaffoldState,
//                viewModel = viewModel,
//                focusManager = focusManager
//            )
//        },
//        drawerBackgroundColor = colorResource(id = R.color.purple_200),
//        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
//        drawerContent = {
//            SideDrawer(
//                scaffoldState = scaffoldState,
//                navController = navController,
//                viewModel = viewModel
//            )
//        },
//    ) {
//        LazyColumn() {
//            item {
//                LearningPoint(
//                    learningPointWord = viewModel.wordFieldState.value,
//                    learningPointGrammar = viewModel.grammarFieldState.value
//                )
//            }
//
//            item {
//                var textFieldHeight by remember { mutableStateOf(250) }
//
//                TextField(textFieldHeight,
//                    modifier = Modifier
//                        .onFocusChanged { focusState ->
//                            when {
//                                focusState.isFocused -> {
//                                    textFieldHeight = 100
//                                }
//                                else ->
//                                    textFieldHeight = 200
//                            }
//                        }
//                        .fillMaxSize()
//                        .focusRequester(focusRequester)
//                )
//            }
//            item {
//                EnterButton(
//                    focusRequester = focusRequester,
//                    coroutineScope = coroutineScope,
//                    listState = listState
//                ) { PracticeCard ->
//                    cardState = cardState + listOf(PracticeCard)
//                }
//            }
//            items(getAllData) { card ->
//                CustomItem(
//                    practiceCard = card,
//                    navController = navController,
//                    expandedState = expandedState == card,
//                    onClick = {
//                        expandedState = if (expandedState == card) null else card
//                    },
//                )
//                Spacer(Modifier.size(10.dp))
//
//            }
//
//        }
//    }
//
//}