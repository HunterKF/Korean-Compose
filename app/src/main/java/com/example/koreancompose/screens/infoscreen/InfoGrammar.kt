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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun InfoGrammar(grammar: String?, grammarDef: String?, grammarExample: String?) {
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
            if (grammar != null) {
                Text(
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold,
                    text = grammar
                )
            }
            if (grammarDef != null) {
                Text(
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    text = viewModel.infoGrammarType.value
                )
            }
        }
        if (grammarDef != null) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 12.dp),
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontWeight = FontWeight.Light,
                text = grammarDef
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
                if (grammarExample != null) {
                    ExampleG("-(으)려고’ is attached to a verb root to indicate the purpose of an action.",grammarExample, "I am going to eat Korean food today.")
                }

            }
        }

    }

}

@Composable
fun ExampleG(grammarExplanation1: String, learningKoreanPoint1: String, learningEnglishPoint1: String) {
    Text(
        modifier = Modifier.padding(vertical = 2.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.DarkGray,
        text = "$grammarExplanation1"
    )
    Column(
        modifier = Modifier.padding(start = 12.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color.DarkGray,
            text = "$learningKoreanPoint1"
        )
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color.DarkGray,
            text = "$learningEnglishPoint1"
        )
    }

}

