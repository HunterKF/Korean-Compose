package com.example.koreancompose.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koreancompose.Screen
import com.example.koreancompose.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    //Decides when to close splash screen and continue application
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    //observed from Application, and value is passed to navGraph directly
    private val _startDestination: MutableState<String> = mutableStateOf(Screen.WelcomeScreen.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                if (completed) {
                    /*THIS SHOULD BE PRACTICESCREEN!!!!*/
                    _startDestination.value = Screen.PracticeScreen.route
                } else {
                    _startDestination.value = Screen.WelcomeScreen.route
                }
            }

            _isLoading.value = false
        }
    }

}