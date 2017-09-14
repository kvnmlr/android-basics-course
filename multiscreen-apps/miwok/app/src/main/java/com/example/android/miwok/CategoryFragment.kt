package com.example.android.miwok

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

open class CategoryFragment : Fragment() {

    open lateinit var wordsEnglish: Array<String>
    open lateinit var wordsMiwok: Array<String>
    open lateinit var imageResrouces: Array<Int>
    open lateinit var audioResrouces: Array<Int>
    open var categoryColor: Int = 0
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

    override fun onStop() {
        Log.i("CategoryFragment", "onStop()")
        super.onStop()
        releaseMediaPlayer()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("CategoryFragment", "onCreateView()")
        val rootView: View = inflater!!.inflate(R.layout.words_list, container, false)

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mAudioManager = activity.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val wordList: ArrayList<Word> = ArrayList()

        wordsEnglish.mapIndexedTo(wordList) { index, value -> Word(value, wordsMiwok[index], imageResrouces[index], audioResrouces[index]) }

        val itemsAdapter = WordAdapter(activity, wordList, categoryColor)
        val listView = rootView.findViewById(R.id.list) as ListView
        listView.adapter = itemsAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            if (wordList[i].hasAudio()) {
                releaseMediaPlayer()

                val result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(activity, wordList[i].getAudioResrouceID())
                    mMediaPlayer?.start()
                    mMediaPlayer?.setOnCompletionListener(mCompletionListener)
                }
            }
        }

        return rootView
    }

    private fun releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.release()
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener)
        }
    }
}
