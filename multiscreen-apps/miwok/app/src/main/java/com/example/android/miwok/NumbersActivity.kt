package com.example.android.miwok

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView

class NumbersActivity : AppCompatActivity() {
    private var wordsList: ArrayList<String> = ArrayList()
    private var wordsArray: Array<String> = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
    private lateinit var words: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)
        var rootView = findViewById(R.id.root_view_numbers) as LinearLayout

        words = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")

        for ((index, value) in words.withIndex()) {
            wordsList.add(value)
            Log.v("NumbersActivity", "$index: $value")
            Log.v("NumbersActivity", "Word at index $index: ${wordsList[index]}")
            Log.v("NumbersActivity", "Word at index $index: ${wordsArray[index]}")
            Log.v("NumbersActivity", "Word at index $index: $value")
            assert(wordsList[index] == wordsArray[index] && wordsList[index] == value)

            var wordView = TextView(this)
            wordView.text = words[index]
            rootView.addView(wordView)
        }
    }
}
