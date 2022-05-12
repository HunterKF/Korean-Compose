package com.example.koreancompose.model

import com.google.gson.annotations.SerializedName

class ModelGrammar {
    @SerializedName("grammar")
    var grammar: String = ""

    @SerializedName("gramInDepth1")
    var gramInDepth1: String = ""

    @SerializedName("inDepth1ExKor")
    var inDepth1ExKor: String = ""

    @SerializedName("inDepth1ExEng")
    var inDepth1ExEng: String = ""

    @SerializedName("gramInDepth2")
    var gramInDepth2: String = ""

    @SerializedName("inDepth2ExKor")
    var inDepth2ExKor: String = ""

    @SerializedName("inDepth2ExEng")
    var inDepth2ExEng: String = ""
}