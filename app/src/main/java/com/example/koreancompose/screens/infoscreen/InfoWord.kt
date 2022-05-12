package com.example.koreancompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoWord(word: String?,
             def: String?,
             wordExKor1: String?,
             wordExEng1: String?,
             wordExKor2: String?,
             wordExEng2: String?,) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (word != null) {
                Text(
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold,
                    text = word
                )
            }
            Text(
                fontSize = MaterialTheme.typography.h6.fontSize,
                text = viewModel.infoWordType.value
                )
        }

        if (def != null) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 12.dp),
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontWeight = FontWeight.Light,
                text = def
            )
        }
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
                if (wordExKor1 != null) {
                    ExampleW(wordExKor1)
                }
                if (wordExEng1 != null) {
                    ExampleW(wordExEng1)
                }
                if (wordExKor2 != null) {
                    ExampleW(wordExKor2)
                }
                if (wordExEng2 != null) {
                    ExampleW(wordExEng2)
                }

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

