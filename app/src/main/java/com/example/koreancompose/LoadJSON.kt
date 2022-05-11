package com.example.koreancompose

import android.content.Context
import android.util.Log
import com.example.koreancompose.model.ModelJSONGrammar
import com.example.koreancompose.model.ModelJSONWord
import com.google.gson.Gson
import java.io.InputStream
import java.lang.Exception

class LoadJSON {
    fun loadWordJson(context: Context) {
        var input: InputStream? = null
        var jsonString: String

        try {
            input = context.assets.open("text.json")

            val size = input.available()

            //Create a buffer with the size
            val buffer = ByteArray(size)

            //Read data from InputStream into the buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)
            val loadedWordList = Gson().fromJson<ModelJSONWord>(
                jsonString,
                ModelJSONWord::class.java
            )
            wordList = loadedWordList
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }
        return
    }

    fun loadGrammarJson(context: Context) {
        var input: InputStream? = null
        var jsonString: String
        try {
            input = context.assets.open("grammar.json")

            val size = input.available()

            //Create a buffer with the size
            val buffer = ByteArray(size)

            //Read data from InputStream into the buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)
            val loadedGrammarList = Gson().fromJson<ModelJSONGrammar>(
                jsonString,
                ModelJSONGrammar::class.java
            )
            grammarList = loadedGrammarList
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }
        return
    }

    var grammarList: ModelJSONGrammar? = null

    var wordList: ModelJSONWord? = null
}