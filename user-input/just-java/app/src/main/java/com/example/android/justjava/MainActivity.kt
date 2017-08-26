package com.example.android.justjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var quantity = 2
    private var PRICE_PER_ITEM = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display(quantity)

        /*
        var textView = TextView(this)
        textView.setText("Wow!")
        setContentView(textView)*/
    }

    fun submitOrder(view: View) {
        val price = calculatePrice()
        val orderSummary = createOrderSummary(price)
        displayMessage(orderSummary)
    }

    private fun calculatePrice(): Int {
        val price = quantity * PRICE_PER_ITEM
        return price
    }

    private fun createOrderSummary(price: Int): String {
        Log.i("MainActivity.java", "Order with price $price has been placed")
        var message = "Name: Java"
        message += "\nQuantity: " + quantity
        message += "\nTotal: $" + price
        message += "\nThank you!"
        return message
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

    private fun displayMessage(message: String) {
        val oderSummaryTextView = findViewById<View>(R.id.order_summary_text_view) as TextView
        oderSummaryTextView.text = message
    }
}