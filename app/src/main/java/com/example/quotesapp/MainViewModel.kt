package com.example.quotesapp

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(var context: Context):ViewModel() {

    var quotesList: Array<Quote> = emptyArray()
    var index: Int = 0

    init {
        quotesList = QuotesFromJson()
    }

    fun getQuote() = quotesList[index]
    fun nextQuote():Quote = if (index != quotesList.size-1 ) quotesList[++index] else{quotesList[index]}
    fun previousQuote():Quote = if (index != 0) quotesList[--index] else{quotesList[index]}


    private fun QuotesFromJson(): Array<Quote> {

        val inputStream = context.assets.open("quotes.json") //To get the json
        val size:Int = inputStream.available() //getting the size to make byteArray
        val buffer = ByteArray(size)  //making the byteArray
        inputStream.read(buffer) //reading json via inputStream
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

}