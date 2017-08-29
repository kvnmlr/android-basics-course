package com.example.android.miwok

import android.content.Intent
import android.view.View

class NumbersClickListener : View.OnClickListener {
    override fun onClick(view: View) {
        val numbersIntent = Intent(view.context, NumbersActivity::class.java)
        view.context.startActivity(numbersIntent)
    }
}
