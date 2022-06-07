package com.example.koreancompose.screens.favoritesscreen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.koreancompose.*
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel


@SuppressLint("UnrememberedMutableState")
@Composable
fun FavoritesScreen(
    viewModel: StoredItemsViewModel =
        StoredItemsViewModel(LocalContext.current.applicationContext as Application)
) {
    val allItems by viewModel.readAllData.observeAsState(mutableListOf())

    SwipeTest(allItems as MutableList<StoredItem>)

}

