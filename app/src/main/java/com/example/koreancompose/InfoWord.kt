package com.example.koreancompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Word() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                text = "가다"
            )
            Text(
                text = "Verb",
                fontSize = MaterialTheme.typography.h6.fontSize
                )
        }

        Text(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 12.dp),
            fontSize = MaterialTheme.typography.body1.fontSize,
            fontWeight = FontWeight.Light,
            text = "go, travel, head for, be bound for"
        )
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp),
            color = Color.Black
        )
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(vertical = 16.dp, horizontal = 12.dp)

        ) {
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp),
                color = Color.Gray
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                ExampleW("나는 집으로 갔어요")
                ExampleW("I went home")
                ExampleW("내일 어디에 가고 싶어요?")
                ExampleW("Where do you want to go tomorrow?")

            }
        }

    }

}

@Composable
fun ExampleW(learningPoint: String) {
    Text(
        modifier = Modifier.padding(vertical = 2.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        color = Color.DarkGray,
        text = "$learningPoint"
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewInfoBottomSheet() {
    Column() {
        Word()
        Grammar()
    }
}
