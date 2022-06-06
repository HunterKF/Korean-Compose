package com.example.koreancompose.screens.favoritesscreen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun SwipeTest(allItems: MutableList<StoredItem>) {


    val context = LocalContext.current
    val application = context.applicationContext as Application
    val storedItemsViewModel = StoredItemsViewModel(application)

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
                items = allItems,
                key = { _, item ->
                    item.hashCode()
                }
            ) { index, item ->
                val state = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            storedItemsViewModel.deleteOne(item.inputtedSentence)
                            allItems.remove(item)
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
                Divider()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SampleItems(item: StoredItem) {
    ListItem(
        text = {
            Text(
                text = "${item.inputtedSentence}"
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



