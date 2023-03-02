package com.example.koreancompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.koreancompose.ui.theme.spacing


@Composable
fun LearningPointGrammar(
    grammar: String,
    gramInDepth1: String,
    inDepth1ExKor: String,
    inDepth1ExEng: String,
    gramInDepth2: String?,
    inDepth2ExKor: String,
    inDepth2ExEng: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small)
                .height(0.5.dp),
            color = Color.LightGray
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onSecondary,
                text = grammar
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small)
                .height(0.5.dp),
            color = Color.LightGray
        )

        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        ) {
            ExampleGLearningPoint(
                gramInDepth1,
                inDepth1ExKor,
                inDepth1ExEng
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp),
            color = Color.LightGray
        )
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        ) {
            ExampleGLearningPoint(
                gramInDepth2.toString(),
                inDepth2ExKor,
                inDepth2ExEng
            )
        }
        Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.extraSmall))

    }
}


@Composable
fun ExampleGLearningPoint(
    grammarExplanation: String,
    learningKoreanPoint: String,
    learningEnglishPoint: String
) {
    if (grammarExplanation != "null") {
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            style = MaterialTheme.typography.body2,
            text = grammarExplanation
        )
    }

    Column(
        modifier = Modifier.padding(MaterialTheme.spacing.small)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            style = MaterialTheme.typography.h3,
            text = learningKoreanPoint
        )
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            style = MaterialTheme.typography.body2,
            text = learningEnglishPoint
        )
    }

}

