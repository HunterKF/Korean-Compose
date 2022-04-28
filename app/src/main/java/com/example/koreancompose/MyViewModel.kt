package com.example.koreancompose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koreancompose.model.PracticeCard

class MyViewModel: ViewModel() {
    val TAG = "MyViewModel"
    val card = PracticeCard("","","")

    //TextField
    val textFieldState = mutableStateOf("")

    fun onTextFieldChange(query: String) {
        this.textFieldState.value = query
    }
    
    //Words for practice
    val word = "가다"
    //Grammar points for practice
    val grammar = "으려고"

    //List of items
    val itemList = listOf<String>()
    val cardList = mutableListOf<PracticeCard>(
        PracticeCard("Hello", "How are you?", "I'm fine, and you?")
    )





}

