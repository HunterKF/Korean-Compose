package com.example.koreancompose.screens.practicescreen.CustomItemFuns

import android.content.Context
import android.widget.Toast
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.viewmodels.StoredItemsViewModel
import com.example.koreancompose.model.PracticeCard
import kotlinx.coroutines.*


@Composable
fun FavoriteFun(
    practiceCard: PracticeCard,
    searchResults: List<StoredItem>,
    storedItemsViewModel: StoredItemsViewModel
) {
    val context = LocalContext.current

    val storedItem = StoredItem(
        0L, practiceCard.word,
        practiceCard.grammar,
        practiceCard.inputtedSentence,
        practiceCard.isClicked.value
    )
    LaunchedEffect(key1 = practiceCard.isClicked) {
        checkIsClicked(
            isClicked = practiceCard.isClicked.value,
            practiceCard = practiceCard,
            searchResults = searchResults
        )
    }
    IconButton(
        modifier = Modifier
            .alpha(ContentAlpha.medium),
        onClick = {
            practiceCard.isClicked.value = !practiceCard.isClicked.value
            isClicked(practiceCard.isClicked.value, storedItemsViewModel, storedItem, context)

        }) {

        Icon(
            tint = if (practiceCard.isClicked.value) Color.Red else Color.DarkGray,
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite"
        )
    }


}

fun isClicked(
    isClicked: Boolean,
    viewModel: StoredItemsViewModel,
    storedItem: StoredItem,
    context: Context
) {

    if (isClicked) {
        viewModel.addStoredItem(
            storedItem
        )
        Toast.makeText(context, "Saved to Favorites!", Toast.LENGTH_SHORT).show()
    } else {
        viewModel.deleteStoredItem(
            storedItem
        )
        Toast.makeText(context, "Deleted from Favorites!", Toast.LENGTH_SHORT).show()

        viewModel.deleteOne(storedItem.inputtedSentence)
    }

}

fun checkIsClicked(
    isClicked: Boolean,
    searchResults: List<StoredItem>,
    practiceCard: PracticeCard
) {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    coroutineScope.launch {
        if (isClicked) {
            if (searchResults.isEmpty()) {
                practiceCard.isClicked.value = false
            }
            if (searchResults.isNotEmpty()) {
                practiceCard.isClicked.value = true
            }
        }
    }

}
