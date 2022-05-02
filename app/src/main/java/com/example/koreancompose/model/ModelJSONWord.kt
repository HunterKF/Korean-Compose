package com.example.koreancompose.model

import com.google.gson.annotations.SerializedName

class ModelJSONWord {
    @SerializedName("data_words")
    var dataWords: ArrayList<ModelWord> = ArrayList()
}