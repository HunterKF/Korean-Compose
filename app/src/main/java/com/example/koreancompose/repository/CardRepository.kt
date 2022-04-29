package com.example.koreancompose.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.koreancompose.ViewModel
import com.example.koreancompose.model.ModelJSON
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
    class LoadWordsAndGrammar {
        fun loadJSONContent(context: Context) {
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
                val loadedList = Gson().fromJson<ModelJSON>(
                    jsonString,
                    ModelJSON::class.java
                )
                this.loadedList = loadedList
                Log.d("fromLoadWordsAndGrammar", "${this.loadedList}")
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                // Must close the stream
                input?.close()
            }
            return
        }

        var loadedList: ModelJSON? = null


    }
}