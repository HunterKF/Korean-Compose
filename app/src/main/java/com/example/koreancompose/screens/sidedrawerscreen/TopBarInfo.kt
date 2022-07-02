package com.example.koreancompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.koreancompose.viewmodels.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBarInfo(
    navController: NavController
) {
    val textState = viewModel.topBarText.value

    TopAppBar(backgroundColor = Color.White) {
        Box(modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    navController.navigate(Screen.PracticeScreen.route)
                }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
            Row(modifier = Modifier.align(Alignment.Center)) {
                Text(text = textState, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = MaterialTheme.colors.primary)

            }
        }
    }
}
