package com.example.koreancompose.model

import com.google.gson.annotations.SerializedName

class ModelJSON {
    @SerializedName("data_words")
    var dataWords: ArrayList<ModelWord> = ArrayList()

    @SerializedName("data_grammar")
    var dataGrammar: ArrayList<ModelGrammar> = ArrayList()
}