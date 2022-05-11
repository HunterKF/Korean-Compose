package com.example.koreancompose

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.model.ModelWord
import com.example.koreancompose.repository.CardRepository

class ViewModel: ViewModel() {

    private val wordModelJSONWord = ModelJSONWord()
    //TextField = mutableStateOf("")
    val textFieldState = mutableStateOf("")
    //Mutable states for learning bar
    var wordFieldState = mutableStateOf("가다")
    var wordDefFieldState = mutableStateOf("to go")

    //Mutable states for grammar
    var grammarFieldState = mutableStateOf("(으)려고")
    var grammarDefFieldState = mutableStateOf("to intend to do something")
    var grammarExampleFieldState = mutableStateOf("(으)려고")

    //Mutable state for InfoWord and InfoGrammar - Just in case I want to add more than verbs
    var infoWordType = mutableStateOf("verb")
    var infoGrammarType = mutableStateOf("grammar")

    //Called in Button(onClick), changes the textFieldState to current textFieldState
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

    var allWords: ModelJSONWord = ModelJSONWord()
    var allGrammar: ModelJSONGrammar = ModelJSONGrammar()


    //Random function, requires an end parameter so you can check the size of the array.
    private fun rand(end: Int): Int {
        val start = 0
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

//returns a random word
    data class WordData(var word: String, var def: String)
    fun returnWord(item: ModelJSONWord): WordData {
        var randomNumber = rand(item.dataWords.size)

        if (randomNumber === item.dataWords.size) {
            randomNumber -= 1
        }
        val grammar = item.dataWords[randomNumber].word
        val def = item.dataWords[randomNumber].def
        return WordData(grammar, def)
    }
//returns a random grammar point
    data class GrammarData(var grammar: String, var def: String, var exampleSentence: String)
    fun returnGrammar(item: ModelJSONGrammar): GrammarData {
        var randomNumber = rand(item.dataGrammar.size)

        if (randomNumber === item.dataGrammar.size) {
            randomNumber -= 1
        }
        val grammar = item.dataGrammar[randomNumber].grammar
        val def = item.dataGrammar[randomNumber].def
        val exampleSentence = item.dataGrammar[randomNumber].exampleSentence
        return GrammarData(grammar, def, exampleSentence)
    }

}

