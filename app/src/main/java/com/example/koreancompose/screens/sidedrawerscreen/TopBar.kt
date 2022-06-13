package com.example.koreancompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.koreancompose.screens.sidedrawerscreen.Body
import com.example.koreancompose.screens.sidedrawerscreen.Header
import com.example.koreancompose.screens.sidedrawerscreen.Items
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
        modifier = Modifier.clickable(
        ) { focusManager.clearFocus() },
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
