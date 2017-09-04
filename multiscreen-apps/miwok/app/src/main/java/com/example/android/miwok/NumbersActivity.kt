package com.example.android.miwok

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*


class NumbersActivity : AppCompatActivity() {
    private var wordsList: ArrayList<String> = ArrayList()
    private var wordsArray: Array<String> = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
    private lateinit var wordsEnglish: Array<String>
    private lateinit var wordsMiwok: Array<String>
    private lateinit var imageResrouces: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_list)

        wordsEnglish = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
        wordsMiwok = arrayOf("lutti", "otiiko", "tolookosu", "oyyisa", "massokka", "temmokka", "kenekaku", "kawinta", "wo'e", "na'aacha")
        imageResrouces = arrayOf(
                R.drawable.number_one,
                R.drawable.number_two,
                R.drawable.number_three,
                R.drawable.number_four,
                R.drawable.number_five,
                R.drawable.number_six,
                R.drawable.number_seven,
                R.drawable.number_eight,
                R.drawable.number_nine,
                R.drawable.number_ten
        )

        var wordList: ArrayList<Word> = ArrayList()

        for ((index, value) in wordsEnglish.withIndex()) {
            wordsList.add(value)
            Log.v("NumbersActivity", "$index: $value")
            Log.v("NumbersActivity", "Word at index $index: ${wordsList[index]}")
            Log.v("NumbersActivity", "Word at index $index: ${wordsArray[index]}")
            Log.v("NumbersActivity", "Word at index $index: $value")
            assert(wordsList[index] == wordsArray[index] && wordsList[index] == value)

            wordList.add(Word(value, wordsMiwok[index], imageResrouces[index]))
        }

        val itemsAdapter = WordAdapter(this, wordList)
        val listView = findViewById(R.id.list) as ListView

        listView.adapter = itemsAdapter
    }
}
