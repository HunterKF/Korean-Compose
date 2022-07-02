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
fun InfoWord(
    word: String,
    def: String,
    wordExKor1: String,
    wordExEng1: String,
    wordExKor2: String,
    wordExEng2: String,
) {
    fun changeTitle () {
        viewModel.topBarText.value = "Information"
    }
    changeTitle()
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.small
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                style = MaterialTheme.typography.body2,
                text = word
            )
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.body2,
                text = def
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
            ExampleEngWInfo(wordExKor1)
            ExampleKorWInfo(wordExEng1)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp),
            color = Color.LightGray
        )
        Column(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small,
                    top = MaterialTheme.spacing.small
                )
        ) {
            ExampleEngWInfo(wordExKor2)
            ExampleKorWInfo(wordExEng2)
        }
    }

}


@Composable
fun ExampleEngWInfo(learningPoint: String) {
    Text(
        modifier = Modifier.padding(vertical = 2.dp),
        style = MaterialTheme.typography.body2,
        text = learningPoint
    )
}

@Composable
fun ExampleKorWInfo(learningPoint: String) {
    Text(
        modifier = Modifier.padding(vertical = 2.dp),
        style = MaterialTheme.typography.h3,
        text = learningPoint
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewInfo() {
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
