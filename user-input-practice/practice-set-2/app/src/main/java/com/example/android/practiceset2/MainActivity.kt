package com.example.android.practiceset2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weekday = 5
        val weekend = 9
        val optimalHours = 7 * 8

        var actualHours = weekday * 5
        actualHours += weekend * 2
        val solution = optimalHours - actualHours
        display(solution)
    }

    fun display(text: String) {
        val t = findViewById<TextView>(R.id.display_text_view) as TextView
        t.text = text
    }

    fun display(text: Int) {
        val t = findViewById<TextView>(R.id.display_text_view) as TextView
        t.text = text.toString() + ""
    }

    fun display1(text: String) {
        display(text)
    }

    fun display2(text: String) {
        val t = findViewById<TextView>(R.id.display_text_view_2) as TextView
        t.text = text
    }

    fun display3(text: String) {
        val t = findViewById<TextView>(R.id.display_text_view_3) as TextView
        t.text = text
    }
}