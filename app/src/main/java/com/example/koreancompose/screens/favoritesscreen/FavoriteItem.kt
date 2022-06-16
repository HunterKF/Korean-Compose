package com.example.koreancompose.screens.favoritesscreen

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.koreancompose.Screen
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.viewmodels.StoredItemsViewModel


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun SwipeTest(allItems: MutableList<StoredItem>, navController: NavController) {


    val context = LocalContext.current
    val application = context.applicationContext as Application
    val storedItemsViewModel = StoredItemsViewModel(application)

    if (allItems.size != 0) {
        storedItemsViewModel.favoriteItemState.value = true
    }

    val openDialog = remember { mutableStateOf(false) }

    when (storedItemsViewModel.favoriteItemState.value) {
        true -> {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(modifier = Modifier.weight(1f)) {

                    itemsIndexed(
                        items = allItems,
                        key = { index, item ->
                            item.hashCode()
                        }
                    ) { index, item ->
                        val state = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    allItems.remove(item)
                                    storedItemsViewModel.deleteOne(item.inputtedSentence)
                                    println(allItems.size)
                                    if (allItems.size == 0) {
                                        storedItemsViewModel.favoriteItemState.value = false
                                    }
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
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Button(modifier = Modifier.fillMaxWidth(),
                                onClick = { openDialog.value = true }) {
                                Text("Delete all")
                            }
                        }
                    }

                }

            }

        }
        false -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No favorites yet?")
                Button(modifier = Modifier.padding(16.dp).fillMaxWidth(), onClick = { navController.navigate(Screen.PracticeScreen.route)}) {
                    Text("Practice now!")
                }
            }

        }

    }


    alertDialog(
        openDialog = openDialog,
        context = context,
        storedItemsViewModel = storedItemsViewModel
    )
}


@ExperimentalMaterialApi
@Composable
fun SampleItems(item: StoredItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                fontWeight = FontWeight.Light,
                text = item.inputtedWord
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontWeight = FontWeight.Light,
                text = item.inputtedGrammar
            )
        }
        Text(
            modifier = Modifier
                .weight(3f),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            text = item.inputtedSentence
        )
    }
}

@Composable
fun alertDialog(
    openDialog: MutableState<Boolean>,
    context: Context,
    storedItemsViewModel: StoredItemsViewModel
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "잠깐만요!!", color = Color.DarkGray) },
            text = {
                Text(
                    text = "Do you want to delete all of your favorited practice? This action cannot be undone.",
                    color = Color.Black
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        openDialog.value = false
                        storedItemsViewModel.deleteAllStoredItem()
                        Toast.makeText(context, "Deleted favorites!", Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = "Confirm", color = Color.DarkGray)
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
//                        Toast.makeText(context, "Dismiss Button Click", Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = "Dismiss", color = Color.DarkGray)
                }
            },
            backgroundColor = Color.White
        )
    }
}



