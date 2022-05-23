package com.example.koreancompose.screens.favoritesscreen

import android.app.Application
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel

@ExperimentalMaterialApi
@Composable
fun swipeToDismiss(sentence: String, word: String, grammar: String) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val storedItemsViewModel = StoredItemsViewModel(application)

    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                storedItemsViewModel.deleteOne(sentence)
            }
            true
        }
    )


    SwipeToDismiss(
        state = dismissState,
        /***  create dismiss alert Background */
        directions = setOf(
            DismissDirection.EndToStart
        ),
        dismissThresholds = { /*direction ->*/
//            FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.5f)
            FractionalThreshold(0.2f)
        },

        background = {
            val color = when (dismissState.dismissDirection) {
                DismissDirection.StartToEnd -> Color.Green
                DismissDirection.EndToStart -> Color.Red
                null -> Color.Transparent
            }
            val direction = dismissState.dismissDirection

            val alignment = Alignment.CenterEnd
            val icon = Icons.Default.Delete

            val scale by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = Dp(20f)),
                contentAlignment = alignment
            ) {
                Icon(
                    icon,
                    contentDescription = "Delete Icon",
                    modifier = Modifier.scale(scale)
                )
            }
        },


        /**** Dismiss Content */

        dismissContent = {
            Card(elevation = animateDpAsState( if (dismissState.dismissDirection != null) 4.dp else 0.dp).value) {
                FavoriteItem(sentence, word, grammar)
            }

        },

    )
}

@Composable
fun FavoriteItem(sentence: String, word: String, grammar: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                fontWeight = FontWeight.Light,
                text = word
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontWeight = FontWeight.Light,
                text = grammar
            )
        }
        Text(
            modifier = Modifier
                .weight(3f),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            text = sentence
        )
    }
    Divider(modifier = Modifier
        .width(1.dp)
        .padding(vertical = 16.dp))
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showSystemUi = true)
@Composable
fun FavoritePreview() {
    swipeToDismiss("hi", "hello", "boo")
}