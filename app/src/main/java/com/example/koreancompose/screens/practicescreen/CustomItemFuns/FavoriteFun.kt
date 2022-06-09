package com.example.koreancompose.screens.practicescreen.CustomItemFuns

import android.app.Application
import android.content.Context
import android.util.Log
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
import com.example.koreancompose.database.StoredItemsViewModel
import com.example.koreancompose.model.PracticeCard


@Composable
fun FavoriteFun(
    practiceCard: PracticeCard
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val storedItemsViewModel = StoredItemsViewModel(application)

    val searchResults = storedItemsViewModel.searchResults.value

    val isClicked = practiceCard.isClicked
    checkRoom(
        practiceCard = practiceCard,
        storedItemsViewModel = storedItemsViewModel,
        searchResults = searchResults
    )

    val storedItem = StoredItem(
        0L, practiceCard.word,
        practiceCard.grammar,
        practiceCard.inputtedSentence,
        practiceCard.isClicked.value
    )


    Log.d("FavoriteFun", "FavoriteFun has been called!")
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

fun checkRoom(
    practiceCard: PracticeCard,
    storedItemsViewModel: StoredItemsViewModel,
    searchResults: List<StoredItem>?
) {


    val TAG = "CHECKROOM"
    storedItemsViewModel.searchStoredItem(practiceCard.inputtedSentence)
    Log.d(TAG, "checkRoom() has been called!")
    if (searchResults.isNullOrEmpty()) {
        practiceCard.isClicked.value = false
    }
}