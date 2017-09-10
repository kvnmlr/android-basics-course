package com.example.android.miwok

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numbers = findViewById(R.id.numbers) as TextView
        val numbersClickListener = NumbersClickListener()
        numbers.setOnClickListener(numbersClickListener)

        /*
        numbers.setOnClickListener({
            val numbersIntent = Intent(this, NumbersActivity::class.java)
            startActivity(numbersIntent)
        })*/

        val family = findViewById(R.id.family) as TextView
        family.setOnClickListener({
            val familyIntent = Intent(this, FamilyActivity::class.java)
            startActivity(familyIntent)
        })

        val colors = findViewById(R.id.colors) as TextView
        colors.setOnClickListener({
            val colorsIntent = Intent(this, ColorsActivity::class.java)
            startActivity(colorsIntent)
        })

        val phrases = findViewById(R.id.phrases) as TextView
        phrases.setOnClickListener(
        {
            val phrasesIntent = Intent(this, PhrasesActivity::class.java)
            startActivity(phrasesIntent)
        })
    }
}