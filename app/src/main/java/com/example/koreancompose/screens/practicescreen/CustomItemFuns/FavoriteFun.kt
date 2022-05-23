package com.example.koreancompose.screens.practicescreen.CustomItemFuns

import android.app.Application
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
    clicked: MutableState<Boolean>,
    practiceCard: PracticeCard
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val storedItemsViewModel = StoredItemsViewModel(application)


    val storedItem = StoredItem(0L, practiceCard.word,
        practiceCard.grammar,
        practiceCard.inputtedSentence)
    IconButton(
        modifier = Modifier
            .alpha(ContentAlpha.medium),
        onClick = {
            clicked.value = !clicked.value
            isClicked(clicked, storedItemsViewModel, storedItem)

        }) {
        Icon(
            tint = if(clicked.value) Color.Yellow else Color.DarkGray,
            imageVector = Icons.Default.Star,
            contentDescription = "Star"
        )
    }
}
fun isClicked(isClicked: MutableState<Boolean>, viewModel: StoredItemsViewModel, storedItem: StoredItem) {

    if (isClicked.value) {
        viewModel.addStoredItem(
            storedItem
            )
        println("The current value of isClicked is ${isClicked.value} 1")
    } else {
        println("The current value of isClicked is ${isClicked.value} 2")
        viewModel.deleteStoredItem(
            storedItem
        )
        viewModel.deleteOne(storedItem.inputtedSentence)
    }
    println("The current value of isClicked is ${isClicked.value} 3")

}