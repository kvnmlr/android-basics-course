package com.example.android.miwok

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class PhrasesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, PhrasesFragment())
                .commit()
    }
}
