package com.example.koreancompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    val textFieldState = mutableStateOf("")

    fun onTextfieldChange(textFieldState: String) {
        this.textFieldState.value = textFieldState
    }



}

