package com.example.koreancompose.viewmodels

import androidx.compose.runtime.mutableStateOf
import com.example.koreancompose.PapagoDTO
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class TranslateViewModel {

    var translatedTextTVM = mutableStateOf("Loading...")


    fun translateText(text: String) {
        val JSON = "application/json; charset=utf-8".toMediaType()

        val client = OkHttpClient()
        val url = "https://openapi.naver.com/v1/papago/n2mt"
        val json = JSONObject()
        json.put("source", "ko")
        json.put("target", "en")
        json.put("text", text)

        val body = json.toString().toRequestBody(JSON)
        val request = Request.Builder()
            .header("X-Naver-Client-Id", "U4ZWGbfLHS9Z8IUiPPBw")
            .addHeader("X-Naver-Client-Secret", "TrWbwi0mWC")
            .url(url)
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                var str = response.body!!.string()


                var papagoDTO = Gson().fromJson<PapagoDTO>(str, PapagoDTO::class.java)
                this@TranslateViewModel.translatedTextTVM.value =
                    papagoDTO.message!!.result!!.translatedText!!


            }

        })
    }


}