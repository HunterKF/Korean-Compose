package com.example.koreancompose

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
    println("This is from the TopBar: the current value for viewModel.topBarText is: ${viewModel.topBarText.value}")
    val textState = viewModel.topBarText.value

    TopAppBar(
        title = { Text(text = textState, fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                focusManager.clearFocus()
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = colorResource(id = R.color.white),
        contentColor = Color.Black
    )
}
