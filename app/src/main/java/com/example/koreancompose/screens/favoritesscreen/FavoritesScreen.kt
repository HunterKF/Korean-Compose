package com.example.koreancompose.screens.favoritesscreen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.koreancompose.*
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoritesScreen(
    viewModel: StoredItemsViewModel =
        StoredItemsViewModel(LocalContext.current.applicationContext as Application)
) {
    val allItems by viewModel.readAllData.observeAsState(mutableListOf())

    SwipeToDelete()
//    MainScreen(
//        allItems = allItems as MutableList<StoredItem>
//    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    allItems: MutableList<StoredItem>
) {
    val listState = rememberLazyListState()

    val context = LocalContext.current
    val application = context.applicationContext as Application

    val storedItemsViewModel = StoredItemsViewModel(application)

    LazyColumn(
        reverseLayout = false,
        modifier = Modifier
            .fillMaxSize()
            .simpleVerticalScrollbar(listState),
        verticalArrangement = Arrangement.Top,
        userScrollEnabled = true,
        state = listState
    ) {

        items(allItems) { item ->
            var unread by remember { mutableStateOf(false) }
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart) {
                        println("The item was removed.")
                        allItems.remove(item)
//                        storedItemsViewModel.deleteOne(item.inputtedSentence)
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier.padding(vertical = 4.dp),
                directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                dismissThresholds = { direction ->
                    FractionalThreshold(if (direction == DismissDirection.StartToEnd) 0.25f else 0.5f)
                },
                background = {
                    val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.LightGray
                            DismissValue.DismissedToEnd -> Color.Green
                            DismissValue.DismissedToStart -> Color.Red
                        }
                    )
                    val alignment = when (direction) {
                        DismissDirection.StartToEnd -> Alignment.CenterStart
                        DismissDirection.EndToStart -> Alignment.CenterEnd
                    }
                    val icon = when (direction) {
                        DismissDirection.StartToEnd -> Icons.Default.Done
                        DismissDirection.EndToStart -> Icons.Default.Delete
                    }
                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = 20.dp),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Localized description",
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
                dismissContent = {
                    Card(
                        elevation = animateDpAsState(
                            if (dismissState.dismissDirection != null) 4.dp else 0.dp
                        ).value
                    ) {
                        ListItem(
                            text = {
                                Text(item.inputtedSentence, fontWeight = if (unread) FontWeight.Bold else null)
                            },
                            secondaryText = { Text("Swipe me left or right!") }
                        )
                    }
                }
            )
        }

    }

}

@ExperimentalMaterialApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun SwipeToDelete() {
    val items = mutableStateListOf(
        "Make it Easy 1",
        "Make it Easy 2",
        "Make it Easy 3",
        "Make it Easy 4",
        "Make it Easy 5",
        "Make it Easy 6",
        "Make it Easy 7",
        "Make it Easy 8",
        "Make it Easy 9",
        "Make it Easy 10"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Swipe to Delete",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        LazyColumn {
            itemsIndexed(
                items = items,
                key = { _, item ->
                    item.hashCode()
                }
            ) { index, item ->
                val state = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            items.remove(item)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = state,
                    background = {
                        val color = when (state.dismissDirection) {
                            DismissDirection.StartToEnd -> Color.Transparent
                            DismissDirection.EndToStart -> Color.Red
                            null -> Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = color)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    },
                    dismissContent = {
                        SampleItems(item)
                    },
                    directions = setOf(DismissDirection.EndToStart)
                )
                Divider(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SampleItems(item: String) {
    ListItem(
        text = {
            Text(
                text = item
            )
        },
        overlineText = {
            Text(
                text = "Welcome To"
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Person"
            )
        },
        trailing = {
            Icon(
                imageVector = Icons.Outlined.ArrowDropDown,
                contentDescription = "DownArrow"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    )
}









