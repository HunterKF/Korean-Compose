package com.example.koreancompose

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.screens.jsonfiles.LoadJsonGrammar
import com.example.koreancompose.screens.jsonfiles.LoadJsonWords
import com.example.koreancompose.ui.theme.KoreanComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        super.onCreate(savedInstanceState)
        setContent {

            KoreanComposeTheme {
//                LoadJsonWords()
//                LoadJsonGrammar()
//                Navigation()
                ScreenSetup()
            }
        }
    }
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f)
        )
        Text(
            head3, color = Color.White,
            modifier = Modifier.weight(0.2f)
        )
    }
}

@Composable
fun ProductRow(sentence: String, word: String, grammar: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            sentence, modifier = Modifier
                .weight(0.1f)
        )
        Text(word, modifier = Modifier.weight(0.2f))
        Text(grammar, modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    )
}

@Composable
fun ScreenSetup(
    viewModel: StoredItemsViewModel =
        StoredItemsViewModel(LocalContext.current.applicationContext as Application)
) {
    val allItems by viewModel.readAllData.observeAsState(listOf())

    MainScreen(
        allProducts = allItems,
        viewModel = viewModel
    )
}

@Composable
fun MainScreen(
    allProducts: List<StoredItem>,
    viewModel: StoredItemsViewModel
) {
    var storedItemSentence by remember { mutableStateOf("") }
    var storedItemWord by remember { mutableStateOf("") }
    var storedItemGrammar by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }

    val onStoredItemSentenceChange = { text: String ->
        storedItemSentence = text
    }

    val onStoredItemWordChange = { text: String ->
        storedItemWord = text
    }
    val onStoredItemGrammarChange = { text: String ->
        storedItemGrammar = text
    }
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CustomTextField(
            title = "Stored Sentence",
            textState = storedItemSentence,
            onTextChange = onStoredItemSentenceChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            title = "Stored Word",
            textState = storedItemWord,
            onTextChange = onStoredItemWordChange,
            keyboardType = KeyboardType.Number
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            androidx.compose.material.Button(onClick = {
                viewModel.addStoredItem(
                    StoredItem(
                        0,
                        storedItemSentence,
                        storedItemWord,
                        storedItemGrammar
                    )
                )
            }) {
                Text("Add")
            }

//            androidx.compose.material.Button(onClick = {
//                searching = true
//                viewModel.findProduct(productName)
//            }) {
//                Text("Search")
//            }

//            androidx.compose.material.Button(onClick = {
//                searching = false
//                viewModel.deleteStoredItem(storedItemSentence)
//            }) {
//                Text("Delete")
//            }

//            androidx.compose.material.Button(onClick = {
//                searching = false
//                productName = ""
//                productQuantity = ""
//            }) {
//                Text("Clear")
//            }

            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
//                val list = if (searching) searchResults else allProducts

                item {
                    TitleRow(head1 = "ID", head2 = "Product", head3 = "Quantity")
                }

//                items(list) { product ->
//                    ProductRow(id = product.id, name = product.productName,
//                        quantity = product.quantity)
//                }
            }

        }
    }
}
