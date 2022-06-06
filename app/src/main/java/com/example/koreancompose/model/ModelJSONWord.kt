package com.example.koreancompose.model

import com.google.gson.annotations.SerializedName

class ModelJSONWord {
    @SerializedName("words")
    var dataWords: ArrayList<ModelWord> = ArrayList()

}