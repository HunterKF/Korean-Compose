package com.example.koreancompose.screens.practicescreen

import android.inputmethodservice.Keyboard
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koreancompose.InfoWord

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LearningPoint(
    learningPointWord: String?,
    learningPointGrammar: String?
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotateStateWord by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)
    val rotateStateGrammar by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.clickable { expandedState = !expandedState }
            ) {
                if (learningPointWord != null) {
                    LearningPoint(text = "가다")
                }
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotateStateWord),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop"
                    )
                }
                Row(
                    modifier = Modifier.clickable { expandedState = !expandedState }
                ) {
                    if (learningPointGrammar != null) {
                        LearningPoint(text = "하다")
                    }
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                            .rotate(rotateStateGrammar),
                        onClick = {
                            expandedState = !expandedState
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop"
                        )
                    }
                }

            }

        }
        if (expandedState) {
            Column(modifier = Modifier.fillMaxWidth()) {
                InfoWord(word = "가다", wordDef = "To go")
            }
        }
    }


}

@Composable
fun LearningPoint(text: String?) {
    if (text != null) {
        Text(text)
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewLearningPoint() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        LearningPoint("가다", "때문에")
    }

}