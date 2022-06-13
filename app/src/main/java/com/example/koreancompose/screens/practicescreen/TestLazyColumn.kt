package com.example.koreancompose.screens.practicescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.viewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun testLazyColumn() {
//    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {


        LazyColumn {
            stickyHeader {
                TextField(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    value = viewModel.textFieldState.value,
                    label = {
                        Text("Start typing now...")
                    },
                    onValueChange = { newValue ->
                        viewModel.onTextFieldChange(newValue)
                    }
                )

                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ }) {
                    Text("Click me!")
                }
            }
            items(20) { index ->
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center) {
                    Text(fontSize = 16.sp,
                        text = "Item: $index")
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun preview() {
    testLazyColumn()
}