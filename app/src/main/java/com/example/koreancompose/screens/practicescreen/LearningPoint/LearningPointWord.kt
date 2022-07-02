package com.example.koreancompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koreancompose.ui.theme.spacing

@Composable
fun LearningPointWord(
    word: String,
    def: String,
    wordExKor1: String,
    wordExEng1: String,
    wordExKor2: String,
    wordExEng2: String,
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
                text = word
            )
        }

        Text(
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.small),
            style = MaterialTheme.typography.body2,
            text = def
        )

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
            ExampleKorW(wordExKor1)
            ExampleEngW(wordExEng1)
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
            ExampleKorW(wordExKor2)
            ExampleEngW(wordExEng2)
        }
        Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.extraSmall))
    }

}


@Composable
fun ExampleEngW(learningPoint: String) {
    Text(
        modifier = Modifier.padding(vertical = 2.dp),
        style = MaterialTheme.typography.body2,
        text = learningPoint
    )
}

@Composable
fun ExampleKorW(learningPoint: String) {
    Text(
        modifier = Modifier.padding(vertical = 2.dp),
        style = MaterialTheme.typography.h3,
        text = learningPoint
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLearningPoint() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        LearningPointWord(
            "가다",
            "to go",
            wordExKor1 = "저는 집으로 갈게요.",
            wordExEng1 = "I'll go home now.",
            wordExKor2 = "넌 마트에 언제 갔어요?",
            wordExEng2 = "When did you go to the mart?"
        )
    }

}
