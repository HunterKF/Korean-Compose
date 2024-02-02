package com.example.koreancompose.screens.favoritesscreen

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.koreancompose.ui.theme.PrimaryOrange
import com.example.koreancompose.ui.theme.elevation
import com.example.koreancompose.ui.theme.spacing
import com.jagerapps.koreancompose.R


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
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = MaterialTheme.spacing.medium)
                ) {
                    item {
                        Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
                    }
                    itemsIndexed(
                        items = allItems,
                        key = { index, item ->
                            item.hashCode()
                        }
                    ) { index, item ->
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Check out what I wrote in ${stringResource(R.string.textfield_type_here)}! ${item.inputtedSentence}"
                            )
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)

                        val state = rememberDismissState(
                            confirmStateChange = { dismissValue ->
                                when (dismissValue) {
                                    DismissValue.Default -> {
                                        false
                                    }
                                    DismissValue.DismissedToStart -> {
                                        allItems.remove(item)
                                        storedItemsViewModel.deleteOne(item.inputtedSentence)
                                        if (allItems.size == 0) {
                                            storedItemsViewModel.favoriteItemState.value = false
                                        }
                                        true

                                    }
                                    DismissValue.DismissedToEnd -> {
                                        context.startActivity(shareIntent)
                                        false
                                    }
                                }
                            }
                        )

                        SwipeToDismiss(
                            state = state,
                            directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                            dismissThresholds = { FractionalThreshold(0.4f)},
                            background = {
                                val direction = state.dismissDirection ?: return@SwipeToDismiss
                                val color = when (state.targetValue) {
                                    DismissValue.DismissedToEnd-> Color.Green.copy(alpha = 0.4f)
                                    DismissValue.DismissedToStart -> Color.Red.copy(alpha = 0.5f)
                                    DismissValue.Default -> Color.LightGray.copy(alpha = 0.5f)
                                }
                                val icon = when (state.targetValue) {
                                    DismissValue.Default -> painterResource(R.drawable.ic_circle_24)
                                    DismissValue.DismissedToEnd -> painterResource(R.drawable.ic_baseline_share_24)
                                    DismissValue.DismissedToStart -> painterResource(R.drawable.ic_delete)
                                }
                                val scale by animateFloatAsState(
                                    if (state.targetValue == DismissValue.Default) 0.85f else 1.2f,
                                    label = ""
                                )
                                val alignment = when (direction) {
                                    DismissDirection.EndToStart -> Alignment.CenterEnd
                                    DismissDirection.StartToEnd -> Alignment.CenterStart
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(MaterialTheme.spacing.small)
                                        .clip(shape = RoundedCornerShape(10.dp))
                                        .background(color = color)
                                        .padding(horizontal = MaterialTheme.spacing.large),
                                    contentAlignment = alignment
                                ) {
                                    Icon(
                                        painter = icon,
                                        contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .scale(scale)
                                    )
                                }
                            },
                            dismissContent = {
                                SampleItems(item)
                            },
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.padding(MaterialTheme.spacing.small))
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .padding(bottom = MaterialTheme.spacing.small)
                                .fillMaxWidth()
                                .shadow(
                                    elevation = MaterialTheme.elevation.small,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clip(shape = RoundedCornerShape(10.dp)),
                            onClick = { openDialog.value = true }) {
                            Text("Delete all")

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
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                        horizontal = MaterialTheme.spacing.medium
                    )
                    .shadow(
                        elevation = MaterialTheme.elevation.small,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
                    .height(50.dp),
                    onClick = { navController.navigate(Screen.PracticeScreen.route) }) {
                    Text("Practice now!")
                }
            }

        }

    }


    AlertDialog(
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
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {

            Row(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.extraSmall)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.h5,
                    text = item.inputtedSentence
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small)
                    .height(0.5.dp),
                color = Color.LightGray
            )
            Row(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.extraSmall)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(
                        end = MaterialTheme.spacing.small
                    ),
                    style = MaterialTheme.typography.body2,
                    text = item.inputtedWord
                )
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.body2,
                    text = item.inputtedGrammar
                )

            }
        }
    }


}


@Composable
fun AlertDialog(
    openDialog: MutableState<Boolean>,
    context: Context,
    storedItemsViewModel: StoredItemsViewModel
) {
    if (openDialog.value) {
        AlertDialog(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
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
fun PopUpMenu(
    modifier: Modifier,
    isClicked: MutableState<Boolean>,
    item: String,
    storedItemsViewModel: StoredItemsViewModel
) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(PrimaryOrange),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Check out what I wrote in ${stringResource(R.string.app_name)}! $item"
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        val context = LocalContext.current


        IconButton(onClick = {
            context.startActivity(shareIntent)
            isClicked.value = false
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_share_20),
                tint = Color.White,
                contentDescription = "Share"
            )
        }
        IconButton(onClick = {
            storedItemsViewModel.deleteOne(item)
            isClicked.value = false
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_delete_20),
                tint = Color.White,
                contentDescription = "Delete"
            )
        }
        IconButton(onClick = { isClicked.value = false }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_close_20),
                tint = Color.White,
                contentDescription = "Delete"
            )
        }
    }
}


