package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteTextID)

    private val author: TextView
        get() = findViewById(R.id.authorID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())

    }
    fun setQuote(quote: Quote){
     quoteText.text = quote.text
     author.text = quote.author
    }

    fun previous(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

    fun next(view: View) {
       setQuote( mainViewModel.nextQuote())
    }

    fun share(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}