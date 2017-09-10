package com.example.android.miwok

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.media.MediaPlayer
import android.widget.AdapterView

class NumbersActivity : AppCompatActivity() {
    private var wordsArray: Array<String> = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
    private lateinit var wordsEnglish: Array<String>
    private lateinit var wordsMiwok: Array<String>
    private lateinit var imageResrouces: Array<Int>
    private lateinit var audioResrouces: Array<Int>
    private var mMediaPlayer: MediaPlayer? = null
    private val mCompletionListener = MediaPlayer.OnCompletionListener {
        releaseMediaPlayer()
    }


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
        audioResrouces = arrayOf(
                R.raw.number_one,
                R.raw.number_two,
                R.raw.number_three,
                R.raw.number_four,
                R.raw.number_five,
                R.raw.number_six,
                R.raw.number_seven,
                R.raw.number_eight,
                R.raw.number_nine,
                R.raw.number_ten
        )

        var wordList: ArrayList<Word> = ArrayList()

        wordsEnglish.mapIndexedTo(wordList) { index, value -> Word(value, wordsMiwok[index], imageResrouces[index], audioResrouces[index]) }

        val itemsAdapter = WordAdapter(this, wordList, R.color.category_numbers)
        val listView = findViewById(R.id.list) as ListView
        listView.adapter = itemsAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            if (wordList[i].hasAudio()) {
                mMediaPlayer = MediaPlayer.create(this@NumbersActivity, wordList[i].getAudioResrouceID())
                releaseMediaPlayer()
                mMediaPlayer!!.start()
                mMediaPlayer!!.setOnCompletionListener(mCompletionListener)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        mMediaPlayer!!.release()
        mMediaPlayer = null
    }
}