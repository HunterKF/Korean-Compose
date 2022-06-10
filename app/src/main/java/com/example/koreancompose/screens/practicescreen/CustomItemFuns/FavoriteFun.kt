package com.example.koreancompose.screens.practicescreen.CustomItemFuns

import android.annotation.SuppressLint
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
import kotlinx.coroutines.*


@SuppressLint("UnrememberedMutableState")
@Composable
fun FavoriteFun(
    practiceCard: PracticeCard,
    searchResults: List<StoredItem>,
    storedItemsViewModel: StoredItemsViewModel
) {
    val context = LocalContext.current


//Hoist the checking function, and then pass in the boolean for isClicked into the function.
    //Maybe checking before the composable is composed would solve the issue...
    val storedItem = StoredItem(
        0L, practiceCard.word,
        practiceCard.grammar,
        practiceCard.inputtedSentence,
        practiceCard.isClicked.value
    )
    LaunchedEffect(key1 = practiceCard.isClicked) {
        storedItemsViewModel.searchStoredItem(practiceCard.inputtedSentence)
        println("LaunchedEffect has been fired!")
    }

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


    println(searchResults.size)

        checkIsClicked(
            isClicked = practiceCard.isClicked.value,
            practiceCard = practiceCard,
            searchResults = searchResults
        )



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
    Log.d(TAG, "${searchResults?.size}")
    if (searchResults.isNullOrEmpty()) {
        practiceCard.isClicked.value = false
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
            delay(1000)
            if (searchResults.isEmpty()) {
                practiceCard.isClicked.value = false
                println("If-and statement has fired! isClicked is now: ${practiceCard.isClicked.value}")
            }
            if (searchResults.isNotEmpty()) {
                practiceCard.isClicked.value = true
                println("If statement has fired! isClicked is now: ${practiceCard.isClicked.value}")
            }
        }
    }

}

fun searchRoom(
    isInRoom: MutableState<Boolean>,
    storedItemsViewModel: StoredItemsViewModel,
    practiceCard: PracticeCard,
    searchResults: List<StoredItem>
): Boolean {
//    storedItemsViewModel.searchStoredItem(practiceCard.inputtedSentence)
    println("The search function has been called!")
    println("The results for searchResult is: ${searchResults.isEmpty()}")
    isInRoom.value = !searchResults.isNullOrEmpty()
    return isInRoom.value

}