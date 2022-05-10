package com.example.koreancompose.model

import com.google.gson.annotations.SerializedName

class ModelJSONGrammar {
    @SerializedName("grammar")
    var dataGrammar: ArrayList<ModelGrammar> = ArrayList()
}