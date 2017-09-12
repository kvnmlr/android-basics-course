package com.example.android.miwok

import android.content.Context
import android.media.AudioManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.media.MediaPlayer
import android.widget.AdapterView
import android.support.v4.app.NavUtils
import android.view.MenuItem
import android.content.Intent
import android.support.v4.app.TaskStackBuilder


class NumbersActivity : AppCompatActivity() {
    private lateinit var wordsEnglish: Array<String>
    private lateinit var wordsMiwok: Array<String>
    private lateinit var imageResrouces: Array<Int>
    private lateinit var audioResrouces: Array<Int>
    private lateinit var mAudioManager: AudioManager
    private var mMediaPlayer: MediaPlayer? = null

    private val mCompletionListener = MediaPlayer.OnCompletionListener {
        releaseMediaPlayer()
    }

    private val mOnAudioFocusChangeListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT, AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                mMediaPlayer?.pause()
                mMediaPlayer?.seekTo(0)
            }
            AudioManager.AUDIOFOCUS_GAIN -> mMediaPlayer?.start()
            AudioManager.AUDIOFOCUS_LOSS -> releaseMediaPlayer()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

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

        val wordList: ArrayList<Word> = ArrayList()

        wordsEnglish.mapIndexedTo(wordList) { index, value -> Word(value, wordsMiwok[index], imageResrouces[index], audioResrouces[index]) }

        val itemsAdapter = WordAdapter(this, wordList, R.color.category_numbers)
        val listView = findViewById(R.id.list) as ListView
        listView.adapter = itemsAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            if (wordList[i].hasAudio()) {
                releaseMediaPlayer()

                val result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(this@NumbersActivity, wordList[i].getAudioResrouceID())
                    mMediaPlayer?.start()
                    mMediaPlayer?.setOnCompletionListener(mCompletionListener)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.release()
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener)
        }
    }

    public override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                val upIntent = NavUtils.getParentActivityIntent(this)
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities()
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}