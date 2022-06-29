package com.example.koreancompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.koreancompose.viewmodels.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    viewModel: ViewModel,
    focusManager: FocusManager
) {
    val textState = viewModel.topBarText.value

    TopAppBar(backgroundColor = Color.White) {
        Box(modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    focusManager.clearFocus()
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                Icon(Icons.Filled.Menu, "")
            }
            Row(modifier = Modifier.align(Alignment.Center)) {
                Text(text = textState, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = MaterialTheme.colors.primary)

            }
        }
    }
//    TopAppBar(
//        modifier = Modifier.background(Color.Black),
//        title = { Text(text = textState, fontSize = 18.sp)} ,
//        navigationIcon = {
//            IconButton(onClick = {
//                focusManager.clearFocus()
//                scope.launch {
//                    scaffoldState.drawerState.open()
//                }
//            }) {
//                Icon(Icons.Filled.Menu, "")
//            }
//        },
//        backgroundColor = colorResource(id = R.color.white),
//        contentColor = Color.Black
//    )
}
