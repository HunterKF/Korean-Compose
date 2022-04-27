package com.example.koreancompose

import android.content.ClipData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koreancompose.repository.data_word
import com.example.koreancompose.ui.theme.KoreanComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var textFieldState by remember {
                mutableStateOf("")
            }
            val dataWord = data_word()
            val getAllData = dataWord.getAllData()


            KoreanComposeTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Greeting(name = "Hunter")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        learningPoint(point = "가다")
                        learningPoint(point = "으려고")
                    }
                    Divider(modifier = Modifier.fillMaxWidth())
                    TextField(
                        value = textFieldState,
                        label = {
                            Text("Start typing nowsassa...")
                        },
                        onValueChange = {
                            textFieldState = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(color = Color.Black)
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                            .padding(16.dp)

                    ) {
                        items(items = getAllData) { card ->
                            CustomItem(card = card)
                        }

                    }

                }
            }
        }
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun learningPoint(point: String) {
    Row() {
        Text("$point")
        Icon(painter = painterResource(id = R.drawable.ic_arrow_drop_down), "down arrow")
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KoreanComposeTheme {
        Greeting("Android")
    }
}