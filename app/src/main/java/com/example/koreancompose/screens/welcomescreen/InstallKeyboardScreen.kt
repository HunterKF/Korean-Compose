package com.example.koreancompose.screens.welcomescreen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
fun InstallKeyboardScreen() {
    val state = rememberScrollState()
    val context = LocalContext.current
    val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
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

    LazyColumn(
        modifier = Modifier
            .scrollable(state, orientation = Orientation.Vertical)
            .fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                InstallItem(item = KeyboardPage.Gboard)
                KeyboardButton("Install gboard") {
                    checkGboard()
                }
            }

        }
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                InstallItem(item = KeyboardPage.CheckKeyboard)
                KeyboardButton("Add Keyboard") {
                    context.startActivity(intent)
                }
            }
        }

    }

}

@Composable
fun InstallItem(item: KeyboardPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .padding(vertical = 60.dp)
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
    }
}

@Composable
fun KeyboardButton(buttonText: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            )
        ) {
            Text(text = buttonText)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    InstallKeyboardScreen()
}