package com.example.koreancompose.screens.practicescreen.CustomItemFuns

import android.app.Application
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel
import com.example.koreancompose.model.PracticeCard


var isClicked = false
@Composable
fun FavoriteFun(
    practiceCard: PracticeCard
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel = StoredItemsViewModel(application)

    IconButton(
        modifier = Modifier
            .alpha(ContentAlpha.medium),
        onClick = {
            isClicked(viewModel, practiceCard)
        }) {
        Icon(
            tint = Color.Yellow,
            imageVector = Icons.Default.Star,
            contentDescription = "Star"
        )
    }
}

fun isClicked(viewModel: StoredItemsViewModel, practiceCard: PracticeCard) {
    if (isClicked) {
        viewModel.addStoredItem(
            StoredItem(
                0L,
                practiceCard.word,
                practiceCard.grammar,
                practiceCard.inputtedSentence
            )
        )
        isClicked = true
    } else {
        /* TODO */
    }
}