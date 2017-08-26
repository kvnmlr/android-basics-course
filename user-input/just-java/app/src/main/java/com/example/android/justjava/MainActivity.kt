package com.example.android.justjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private var quantity = 2
    private var pricePerItem = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display(quantity)
    }

    fun submitOrder(view: View) {
        var priceMessage = "Total item count: " + quantity + " coffees"
        priceMessage += "\n$" + quantity * pricePerItem
        priceMessage += "\nThank You!"

        displayMessage(priceMessage)
    }

    fun increment(view: View) {
        quantity++
        display(quantity)
    }

    fun decrement(view: View) {
        quantity--
        display(quantity)
    }

    private fun display(number: Int) {
        val quantityTextView = findViewById<TextView>(R.id.quantity_text_view) as TextView
        quantityTextView.text = number.toString()
    }

    private fun displayPrice(number: Int) {
        val priceTextView = findViewById<View>(R.id.price_text_view) as TextView
        priceTextView.text = NumberFormat.getCurrencyInstance().format(number)
    }

    private fun displayMessage(message: String) {
        val priceTextView = findViewById<View>(R.id.price_text_view) as TextView
        priceTextView.text = message
    }
}