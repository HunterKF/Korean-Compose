package com.example.koreancompose

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.model.PracticeCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomItem(practiceCard: PracticeCard) {
    var expandedState by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)


    Card(
        modifier = Modifier
            .shadow(2.dp, RoundedCornerShape(16.dp), true)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${practiceCard.word}",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )
                    Text(
                        text = "${practiceCard.grammar}",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Light
                    )
                }
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                )
                Text(
                    modifier = Modifier
                        .weight(4f),
                    text = "${practiceCard.inputedSentence}",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp

                )
                Spacer(modifier = Modifier.height(10.dp))
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotateState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop"
                    )
                }
            }

            if (expandedState) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("X")
                    Text("X")
                    Text("X")
                    Text("X")
                }
            }
        }


    }
}

@Preview
@Composable
fun CustomItemPreview() {
    CustomItem(practiceCard = PracticeCard("가다", "으려고", "가려고 했어요."))
}