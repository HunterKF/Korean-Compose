package com.example.koreancompose.model

import com.google.gson.annotations.SerializedName

class ModelWord {
    @SerializedName("word")
    var word: String = ""

    @SerializedName("def")
    var def: String = ""

    @SerializedName("wordExKor1")
    var wordExKor1: String = ""

    @SerializedName("wordExEng1")
    var wordExEng1: String = ""

    @SerializedName("wordExKor2")
    var wordExKor2: String = ""

    @SerializedName("wordExEng2")
    var wordExEng2: String = ""

}