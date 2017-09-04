package com.example.android.miwok

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class PhrasesActivity : AppCompatActivity() {
    private lateinit var wordsEnglish: Array<String>
    private lateinit var wordsMiwok: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_list)

        wordsEnglish = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
        wordsMiwok = arrayOf("lutti", "otiiko", "tolookosu", "oyyisa", "massokka", "temmokka", "kenekaku", "kawinta", "wo'e", "na'aacha")
        var wordList: ArrayList<Word> = ArrayList()

        wordsEnglish.mapIndexedTo(wordList) { index, value -> Word(value, wordsMiwok[index]) }

        val itemsAdapter = WordAdapter(this, wordList)
        val listView = findViewById(R.id.list) as ListView

        listView.adapter = itemsAdapter
    }
}
