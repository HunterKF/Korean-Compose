package com.example.koreancompose.screens.favoritesscreen

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.*
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel


@Composable
fun FavoritesScreen(
    viewModel: StoredItemsViewModel =
        StoredItemsViewModel(LocalContext.current.applicationContext as Application)
) {
    val allItems by viewModel.readAllData.observeAsState(mutableListOf())


    MainScreen(
        allItems = allItems as MutableList<StoredItem>
    )
}

@Composable
fun MainScreen(
    allItems: MutableList<StoredItem>
) {
    val listState = rememberLazyListState()

    LazyColumn(
        reverseLayout = false,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .simpleVerticalScrollbar(listState),
        verticalArrangement = Arrangement.Top,
        userScrollEnabled = true,
        state = listState
    ) {

//                item {
//                    TitleRow(head1 = "ID", head2 = "Product", head3 = "Quantity")
//                }

        items(allItems) { item ->
            FavoriteItem(item.inputtedSentence, item.inputtedWord, item.inputtedGrammar)
        }

//                items(list) { product ->
//                    ProductRow(id = product.id, name = product.productName,
//                        quantity = product.quantity)
//                }
    }

}


