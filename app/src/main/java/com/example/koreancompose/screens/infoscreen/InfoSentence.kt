package com.example.koreancompose.screens.infoscreen

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InfoSentence(textFieldState: String) {
    Text(
        fontSize = MaterialTheme.typography.h6.fontSize,
        fontWeight = FontWeight.Medium,
        text = "$textFieldState"
    )
}
@Preview(showSystemUi = true)
@Composable
fun PreviewInfoSentence() {
    InfoSentence(textFieldState = "저는 산책하거나 자전거를 타고 싶은 사람이 있냐고 물어봤어요. 왜냐하면 제가 운동하고 싶은 마음이 가졌는데 혼자 하는 것은 좀 외로워요.")
}