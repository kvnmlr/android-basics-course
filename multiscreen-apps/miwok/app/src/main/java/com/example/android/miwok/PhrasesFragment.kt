package com.example.android.miwok

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PhrasesFragment : CategoryFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.wordsEnglish = arrayOf("Hello I'm ...", "What a nice day!", "orange", "four", "five", "six", "seven", "eight", "nine", "ten")
        super.wordsMiwok = arrayOf("lutti", "otiiko", "tolookosu", "oyyisa", "massokka", "temmokka", "kenekaku", "kawinta", "wo'e", "na'aacha")
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
        categoryColor = R.color.category_phrases

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
