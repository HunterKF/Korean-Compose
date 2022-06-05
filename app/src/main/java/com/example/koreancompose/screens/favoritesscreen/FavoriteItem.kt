package com.example.koreancompose.screens.favoritesscreen

import android.app.Application
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.R
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StyledCard(
    modifier: Modifier,
    storedItem: StoredItem,
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val storedItemsViewModel = StoredItemsViewModel(application)


    val shape = RoundedCornerShape(30.dp)
    val backgroundColor = Color(240, 240, 240)
    val dismissState = rememberDismissState(confirmStateChange = { dismissValue ->
        when (dismissValue) {
            DismissValue.Default -> { // dismissThresholds 만족 안한 상태
                false
            }
            DismissValue.DismissedToEnd -> { // -> 방향 스와이프 (수정)
                Toast.makeText(context, "Still in progress", Toast.LENGTH_SHORT).show()
                false
            }
            DismissValue.DismissedToStart -> { // <- 방향 스와이프 (삭제)
                storedItemsViewModel.deleteOne(storedItem.inputtedSentence)
                true
            }
        }
    })

    SwipeToDismiss(
        state = dismissState,
        dismissThresholds = { FractionalThreshold(0.25f) },
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape),
        dismissContent = { // content
            FavoriteItem(
                storedItem = storedItem
            )
        },
        background = { // dismiss content
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> backgroundColor.copy(alpha = 0.5f) // dismissThresholds 만족 안한 상태
                    DismissValue.DismissedToEnd -> Color.Green.copy(alpha = 0.4f) // -> 방향 스와이프 (수정)
                    DismissValue.DismissedToStart -> Color.Red.copy(alpha = 0.5f) // <- 방향 스와이프 (삭제)
                }
            )
            val icon = when (dismissState.targetValue) {
                DismissValue.Default -> painterResource(R.drawable.ic_circle)
                DismissValue.DismissedToEnd -> painterResource(R.drawable.ic_edit_24)
                DismissValue.DismissedToStart -> painterResource(R.drawable.ic_delete)
            }
            val scale by animateFloatAsState(
                when (dismissState.targetValue == DismissValue.Default) {
                    true -> 0.8f
                    else -> 1.5f
                }
            )
            val alignment = when (direction) {
                DismissDirection.EndToStart -> Alignment.CenterEnd
                DismissDirection.StartToEnd -> Alignment.CenterStart
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 30.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    modifier = Modifier.scale(scale),
                    painter = icon,
                    contentDescription = null
                )
            }
        }
    )
}


@Composable
fun FavoriteItem(storedItem: StoredItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                fontWeight = FontWeight.Light,
                text = storedItem.inputtedWord
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontWeight = FontWeight.Light,
                text = storedItem.inputtedGrammar
            )
        }
        Text(
            modifier = Modifier
                .weight(3f),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            text = storedItem.inputtedSentence
        )
    }
    Divider(
        modifier = Modifier
            .width(1.dp)
            .padding(vertical = 16.dp)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showSystemUi = true)
@Composable
fun FavoritePreview() {
//    swipeToDismiss("hi", "hello", "boo")
}