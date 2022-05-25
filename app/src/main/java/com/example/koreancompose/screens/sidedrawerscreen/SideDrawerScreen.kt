package com.example.koreancompose.screens.sidedrawerscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SideDrawer() {
    val state = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    MaterialTheme {
        ModalDrawer(
            modifier = Modifier.fillMaxWidth(),
            scrimColor = Color.Blue,
            drawerBackgroundColor = Color.White,
            drawerElevation = 5.dp,
            drawerShape = RoundedCornerShape(topEnd = 16.dp),
            gesturesEnabled = true,
            drawerState = state,
            drawerContent = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hello")
                    Text("Hello")
                    Text("Hello")
                    Text("Hello")
                }
            }
        ) {

        }

    }
}

@Preview
@Composable
fun preview() {
    SideDrawer()
}