package com.example.koreancompose.screens.welcomescreen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InstallKeyboardScreen() {

    val pagerState = rememberPagerState()

    val pages = listOf(
        KeyboardPage.Gboard,
        KeyboardPage.CheckKeyboard
    )




    Box(modifier = Modifier.fillMaxSize()) {
        VerticalPager(
//            modifier = Modifier.weight(8f),
            state = pagerState,
            horizontalAlignment = Alignment.CenterHorizontally,
            count = 2
        ) { position ->
            InstallItem(item = pages[position], pagerState = pagerState)
        }
        VerticalPagerIndicator(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 6.dp),
            pagerState = pagerState)
    }

}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun InstallItem(item: KeyboardPage, pagerState: PagerState) {

    val context = LocalContext.current
    val gboardPlayStoreIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("market://details?id=com.google.android.inputmethod.latin")
    )
    val gboardSiteIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.inputmethod.latin")
    )

    fun checkGboard() {
        try {
            context.startActivity(gboardPlayStoreIntent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(gboardSiteIntent)
        }
    }

    val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = item.image),
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = item.title,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = item.description,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.weight(1f).padding(horizontal = 20.dp)) {
            Button(
                onClick = {
                    if (pagerState.currentPage == 0) {
                        checkGboard()
                    } else {
                        context.startActivity(intent)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = item.buttonText)
            }
        }

    }

}

@Composable
fun KeyboardButton(modifier: Modifier, buttonText: String, onClick: () -> Unit) {


}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    InstallKeyboardScreen()
}