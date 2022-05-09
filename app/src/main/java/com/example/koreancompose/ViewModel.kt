package com.example.koreancompose

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.repository.CardRepository

class ViewModel: ViewModel() {
    val cardRepo = CardRepository()
    val mainActivity = MainActivity()
    val TAG = "MyViewModel"

    //TextField
    val textFieldState = mutableStateOf("")

    fun onTextFieldChange(query: String) {
        this.textFieldState.value = query
    }
    
    //Words for practice
    val word = "가다"
    //Grammar points for practice
    val grammar = "으려고"
    //Captured user inputted sentence
    var sentence = ""

    //List of items
    val itemList = listOf<String>()


    //Random function, requires an end parameter so you can check the size of the array.
    private fun rand(end: Int): Int {
        val start = 0
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

}

