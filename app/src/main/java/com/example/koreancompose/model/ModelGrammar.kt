package com.example.koreancompose.model

import com.google.gson.annotations.SerializedName

class ModelGrammar {
    @SerializedName("grammar")
    var grammar: String = ""

    @SerializedName("def")
    var def: String = ""

    @SerializedName("example")
    var exampleSentence: String = ""
}