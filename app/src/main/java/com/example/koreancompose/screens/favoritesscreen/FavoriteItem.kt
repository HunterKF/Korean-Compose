package com.example.koreancompose.screens.favoritesscreen

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koreancompose.Screen
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.viewmodels.StoredItemsViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.koreancompose.R
import com.example.koreancompose.ui.theme.PrimaryOrange
import com.example.koreancompose.ui.theme.elevation
import com.example.koreancompose.ui.theme.spacing


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
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = MaterialTheme.spacing.medium)
                ) {

                    items(allItems) { item ->
                        SampleItems(item = item)

                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = MaterialTheme.spacing.small)
                                .shadow(
                                    elevation = MaterialTheme.elevation.small,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clip(shape = RoundedCornerShape(10.dp)),
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
                Button(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                    onClick = { navController.navigate(Screen.PracticeScreen.route) }) {
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

    Box(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small)
            .shadow(
                elevation = MaterialTheme.elevation.small,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        IconButton(modifier = Modifier
            .offset(x = 1.dp, y = -8.dp)
            .align(Alignment.TopEnd), onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.ic_more_horiz), tint = Color.Gray, contentDescription = "")
            PopUpMenu(modifier = Modifier.offset(x = -5.dp, y = -10.dp))
        }
        Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {

            Row(modifier = Modifier.padding(top = MaterialTheme.spacing.medium)) {
                Text(
                    modifier = Modifier.padding(
                        end = MaterialTheme.spacing.small
                    ),
                    style = MaterialTheme.typography.h6,
                    text = "KO"
                )
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.h5,
                    text = item.inputtedSentence
                )
            }
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
            Row(modifier = Modifier) {
                Text(
                    modifier = Modifier.padding(
                        end = MaterialTheme.spacing.small
                    ),
                    style = MaterialTheme.typography.h6,
                    text = "EN"
                )
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.body2,
                    text = "This is a translation of the text. There is no translation as of yet."
                )

            }
        }
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

@Composable
fun PopUpMenu(modifier: Modifier) {
    Row(
        modifier = Modifier
            .background(PrimaryOrange)
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_share_24),
                tint = Color.White,
                contentDescription = "Share"
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                tint = Color.White,
                contentDescription = "Delete"
            )
        }
    }
}

@Preview
@Composable
fun PopUpPreview() {
    PopUpMenu(Modifier.offset())
}

