package com.example.koreancompose.screens.favoritesscreen

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.koreancompose.*
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel


@Composable
fun FavoritesScreen(
    viewModel: StoredItemsViewModel =
        StoredItemsViewModel(LocalContext.current.applicationContext as Application)
) {
    val allItems by viewModel.readAllData.observeAsState(mutableListOf())

    SwipeTest(allItems as MutableList<StoredItem>)

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
            swipeToDismiss(item.inputtedSentence, item.inputtedWord, item.inputtedGrammar)
        }

    }

}







