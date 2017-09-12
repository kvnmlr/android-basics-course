package com.example.android.miwok

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class NumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, NumbersFragment())
                .commit()
    }
}