package com.example.koreancompose

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.model.Card

@Composable
fun CustomItem(card: Card) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .shadow(2.dp, RoundedCornerShape(16.dp), true)
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${card.word}",
                color = Color.DarkGray,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier
                .height(10.dp)
            )
            Text(
                text = "${card.grammar}",
                color = Color.DarkGray,
                fontWeight = FontWeight.Light
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Text(
            modifier = Modifier
                .weight(3f),
            text = "${card.example}",
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp

        )
    }
}