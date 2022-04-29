package com.example.koreancompose.model

import com.google.gson.annotations.SerializedName

class ModelWord {
    @SerializedName("word")
    var word: String = ""

    @SerializedName("def")
    var def: String = ""
}