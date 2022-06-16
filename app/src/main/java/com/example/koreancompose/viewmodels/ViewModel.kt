package com.example.koreancompose.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.koreancompose.TAG
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.model.ModelJSONWord

open class ViewModel : ViewModel() {

    private val wordModelJSONWord = ModelJSONWord()


    //TextField = mutableStateOf("")
    val textFieldState = mutableStateOf("")


    //Mutable states for learning bar
    var wordFieldState = mutableStateOf("가다")
    var wordDefFieldState = mutableStateOf("to go")
    var wordExKor1 = mutableStateOf("1")
    var wordExEng1 = mutableStateOf("2")
    var wordExKor2 = mutableStateOf("3")
    var wordExEng2 = mutableStateOf("4")

    //Mutable states for grammar
    var grammarFieldState = mutableStateOf("(으)려고")
    var grammarInD1State = mutableStateOf("to intend to do something")
    var grammarInD1ExKor = mutableStateOf("")
    var grammarInD1ExEng = mutableStateOf("")
    var grammarInD2State = mutableStateOf("Does it work here")
    var grammarInD2ExKor = mutableStateOf("Does it work here")
    var grammarInD2ExEng = mutableStateOf("Does it work here")

    //Mutable state for InfoWord and InfoGrammar - Just in case I want to add more than verbs
    var infoWordType = mutableStateOf("verb")
    var infoGrammarType = mutableStateOf("grammar")

    //Counter for the scrollToIndex state in Button
    var indexCounter = 0
    fun incrementIndexScrollTo() {
        indexCounter++
    }



    //Called in Button(onClick), changes the textFieldState to current textFieldState
    fun onTextFieldChange(query: String) {
        this.textFieldState.value = query
    }

    //CustomItem card's ExpandedState
//    var expandedState = mutableStateOf(false)

    //Words for practice
    val word = "가다"

    //Grammar points for practice
    val grammar = "으려고"

    //Captured user inputted sentence
    var sentence = ""

    //List of items
    val itemList = listOf<String>()

    var allWords: ModelJSONWord = ModelJSONWord()
    var allGrammar: ModelJSONGrammar = ModelJSONGrammar()

    //isClicked for Favorite fun
    var isClicked = mutableStateOf(false)


    //Random function, requires an end parameter so you can check the size of the array.
    private fun rand(end: Int): Int {
        val start = 0
        require(start <= end) { "Illegal Argument" }
        return (0..end).shuffled().last()
    }
    //returns a random word
    data class RandomWordData(
        var word: String,
        var def: String,
        var wordExKor1: String,
        var wordExEng1: String,
        var wordExKor2: String,
        var wordExEng2: String,
    )

    fun returnWord(item: ModelJSONWord): RandomWordData {

        var randomNumber = rand(item.dataWords.size)
        Log.d(TAG, "The value of randomNumber in returnWord is $randomNumber")

        if (randomNumber === item.dataWords.size) {
            randomNumber -= 1
        }
        val grammar = item.dataWords[randomNumber].word
        val def = item.dataWords[randomNumber].def
        val wordExKor1 = item.dataWords[randomNumber].wordExKor1
        val wordExEng1 = item.dataWords[randomNumber].wordExEng1
        val wordExKor2 = item.dataWords[randomNumber].wordExKor2
        val wordExEng2 = item.dataWords[randomNumber].wordExEng2
        return RandomWordData(grammar, def, wordExKor1, wordExEng1, wordExKor2, wordExEng2)
    }

    //returns a random grammar point
    data class GrammarData(
        var grammar: String,
        var gramInDepth1: String,
        var inDepth1ExKor: String,
        var inDepth1ExEng: String,
        var gramInDepth2: String,
        var inDepth2ExKor: String,
        var inDepth2ExEng: String,
    )

    fun returnGrammar(item: ModelJSONGrammar): GrammarData {
        var randomNumber = rand(item.dataGrammar.size)
        Log.d(TAG, "The value of randomNumber in returnGrammar is $randomNumber")

        if (randomNumber === item.dataGrammar.size) {
            randomNumber -= 1
        }
        val grammar = item.dataGrammar[randomNumber].grammar
        val gramInDepth1 = item.dataGrammar[randomNumber].gramInDepth1
        val inDepth1ExKor = item.dataGrammar[randomNumber].inDepth1ExKor
        val inDepth1ExEng = item.dataGrammar[randomNumber].inDepth1ExEng
        val gramInDepth2 = item.dataGrammar[randomNumber].gramInDepth2
        val inDepth2ExKor = item.dataGrammar[randomNumber].inDepth2ExKor
        val inDepth2ExEng = item.dataGrammar[randomNumber].inDepth2ExEng
        return GrammarData(grammar, gramInDepth1, inDepth1ExKor, inDepth1ExEng, gramInDepth2, inDepth2ExKor, inDepth2ExEng)

    }

    //TopBar text. On loading will make it practice by default
    val topBarText = mutableStateOf("Practice")

    //Textheight for the textField
    var textFieldHeight = mutableStateOf(200)



}

