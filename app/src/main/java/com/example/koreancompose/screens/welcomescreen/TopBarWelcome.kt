package com.example.koreancompose.screens.welcomescreen

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import com.example.koreancompose.Screen
import com.example.koreancompose.viewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TopBarWelcome(
) {
    val textState = "Keyboard"

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher


    TopAppBar(backgroundColor = Color.White) {
        Box(modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    onBackPressedDispatcher?.onBackPressed()
                }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
            Row(modifier = Modifier.align(Alignment.Center)) {
                Text(text = textState, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = MaterialTheme.colors.primary)

            }
        }
    }
}
