package com.example.koreancompose.screens.welcomescreen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.koreancompose.Screen
import com.example.koreancompose.TopBarInfo
import com.example.koreancompose.ui.theme.PrimaryOrange
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun InstallKeyboardScreen(navController: NavHostController) {

    val pagerState = rememberPagerState()

    val pages = listOf(
        KeyboardPage.Gboard,
        KeyboardPage.CheckKeyboard
    )
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))


    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            Modifier.background(androidx.compose.material.MaterialTheme.colors.background),
            scaffoldState = scaffoldState,
            topBar = {
                TopBarWelcome()
            },

            ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                VerticalPager(
                    modifier = Modifier.weight(10f),
                    state = pagerState,

                    horizontalAlignment = Alignment.CenterHorizontally,
                    count = 2
                ) { position ->
                    InstallItem(item = pages[position], pagerState = pagerState)
                }
                Spacer(modifier = Modifier.weight(1f))

                KeyboardButton(modifier = Modifier.weight(1f), pagerState = pagerState)


                Spacer(modifier = Modifier.weight(1f))
            }

        }

        VerticalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 6.dp),
            activeColor = PrimaryOrange,
            inactiveColor = Color.Gray,
            indicatorWidth = 8.dp,
            indicatorHeight = 20.dp,
            pagerState = pagerState
        )
    }

}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun InstallItem(item: KeyboardPage, pagerState: PagerState) {


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .scale(0.7f)
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.6f),
            painter = painterResource(id = item.image),
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = item.title,
            style = androidx.compose.material.MaterialTheme.typography.h1,
            fontSize = 32.sp,
            color = PrimaryOrange,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = item.description,
            style = androidx.compose.material.MaterialTheme.typography.h1,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

    }

}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun KeyboardButton(
    modifier: Modifier,
    pagerState: PagerState
) {
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
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 0 || pagerState.currentPage == 1
        ) {
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
                Text(text = if (pagerState.currentPage == 0) "Install Gboard" else "Add Korean")
            }
        }
    }
}

@Composable
fun KeyboardButton(modifier: Modifier, buttonText: String, onClick: () -> Unit) {


}

