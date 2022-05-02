package com.example.koreancompose.repository

import android.content.Context
import android.util.Log
import com.example.koreancompose.MainActivity
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.model.ModelJSONWord
import com.example.koreancompose.model.PracticeCard
import java.io.InputStream
import java.lang.Exception

import com.google.gson.Gson


class CardRepository {

    val allCards = mutableListOf<PracticeCard>(
    )

    fun getAllData(): List<PracticeCard> {
        return allCards
    }

    //Load text.json
    //I'll get back to this later...
//    class LoadWordsAndGrammar {
//        //Loading JSON file for words into the project
//        fun loadWordJSONContent(context: Context) {
//            var input: InputStream? = null
//            var jsonString: String
//
//            try {
//                input = context.assets.open("text.json")
//
//                val size = input.available()
//
//                //Create a buffer with the size
//                val buffer = ByteArray(size)
//
//                //Read data from InputStream into the buffer
//                input.read(buffer)
//
//                // Create a json String
//                jsonString = String(buffer)
//                val loadedList = Gson().fromJson<ModelJSONWord>(
//                    jsonString,
//                    ModelJSONWord::class.java
//                )
//                this.loadedWordList = loadedList
//                Log.d("fromLoadWordsAndGrammar", "${this.loadedWordList}")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            } finally {
//                // Must close the stream
//                input?.close()
//            }
//            return
//        }
//
//        var loadedWordList: ModelJSONWord? = null
//
//        //Loading JSON file for grammar into the project
//        fun loadGrammarJSONContent(context: Context) {
//            var input: InputStream? = null
//            var jsonString: String
//
//            try {
//                input = context.assets.open("text.json")
//
//                val size = input.available()
//
//                //Create a buffer with the size
//                val buffer = ByteArray(size)
//
//                //Read data from InputStream into the buffer
//                input.read(buffer)
//
//                // Create a json String
//                jsonString = String(buffer)
//                val loadedList = Gson().fromJson<ModelJSONGrammar>(
//                    jsonString,
//                    ModelJSONGrammar::class.java
//                )
//                this.loadedGrammarList = loadedList
//                Log.d("fromLoadWordsAndGrammar", "${this.loadedGrammarList}")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            } finally {
//                // Must close the stream
//                input?.close()
//            }
//            return
//        }
//
//        var loadedGrammarList: ModelJSONGrammar? = null
//    }


}