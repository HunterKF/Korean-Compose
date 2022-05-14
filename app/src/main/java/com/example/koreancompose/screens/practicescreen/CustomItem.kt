package com.example.koreancompose

import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.koreancompose.model.PracticeCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomItem(
    practiceCard: PracticeCard,
    navController: NavController,
    expandedState: Boolean,
    onClick: () -> Unit
) {
    //For the share sheet
    val appName = "Korean Practice"
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "Check out what I wrote in $appName! ${practiceCard.inputtedSentence}"
        )
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .shadow(2.dp, RoundedCornerShape(16.dp), true)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
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
                        text = practiceCard.word,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )
                    Text(
                        text = practiceCard.grammar,
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
                    text = practiceCard.inputtedSentence,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp

                )

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
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                            .weight(1f),
                        onClick = {
                            context.startActivity(shareIntent)
                        }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                            .weight(1f),
                        onClick = {

                        }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Copy"
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                            .weight(1f),
                        onClick = {
                            /* TODO */
                        }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star"
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                            .weight(1f),
                        onClick = {
                            navController.navigate(
                                Screen.InfoScreen.withArgs(
                                    practiceCard.inputtedSentence,
                                    practiceCard.word,
                                    practiceCard.wordDef,
                                    practiceCard.wordExampleKorean1,
                                    practiceCard.wordExampleEnglish1,
                                    practiceCard.wordExampleKorean2,
                                    practiceCard.wordExampleEnglish2,
                                    practiceCard.grammar,
                                    practiceCard.gramInDepth1,
                                    practiceCard.inDepth1ExKor,
                                    practiceCard.inDepth1ExEng,
                                    practiceCard.gramInDepth2,
                                    practiceCard.inDepth2ExKor,
                                    practiceCard.inDepth2ExEng
                                )
                            )
                        }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info"
                        )
                    }
                }
            }
        }


    }
}

//@Preview
//@Composable
//fun CustomItemPreview() {
//    CustomItem(practiceCard = PracticeCard("가다", "으려고", "가려고 했어요."),)
//}
