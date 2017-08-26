package com.example.android.justjava

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {
    private var quantity = 1
    private var PRICE_PER_ITEM = 5
    private var withWhippedCream = false
    private var withChocolate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display(quantity)

        findViewById<TextView>(R.id.toppings_text_view).setText(R.string.toppings)
        findViewById<TextView>(R.id.qty_text_view).setText(R.string.quantity)
        findViewById<TextView>(R.id.order_text_view).setText(R.string.order_summary)

        /*
        var textView = TextView(this)
        textView.setText("Wow!")
        setContentView(textView)*/
    }

    fun submitOrder(view: View) {
        withWhippedCream = findViewById<CheckBox>(R.id.whipped_cream_check_box).isChecked
        withChocolate = findViewById<CheckBox>(R.id.chocolate_check_box).isChecked
        val nameEditText = findViewById<EditText>(R.id.name_edit_text)
        var name = nameEditText.text.toString()
        val price = calculatePrice()
        val orderSummary = createOrderSummary(name, price)
        //displayMessage(orderSummary)
        sendOrder(name, orderSummary)
    }

    private fun calculatePrice(): Int {
        var extras = 0
        if (withWhippedCream) {
            extras++
        }
        if (withChocolate) {
            extras++
        }
        val price = quantity * (PRICE_PER_ITEM + extras)
        return price
    }

    private fun createOrderSummary(name: String, price: Int): String {
        var message = getString(R.string.order_summary_name, name)
        message += "\nQuantity: " + quantity
        message += "\nWhipped Cream: " + withWhippedCream
        message += "\nChocolate: " + withChocolate
        message += "\nTotal: $" + price
        message += getString(R.string.thank_you)
        return message
    }

    fun increment(view: View) {
        if (quantity < 100) {
            quantity++
            display(quantity)
        }
    }

    fun decrement(view: View) {
        if (quantity > 1) {
            quantity--
            display(quantity)
        }
    }

    private fun display(number: Int) {
        val quantityTextView = findViewById<TextView>(R.id.quantity_text_view) as TextView
        quantityTextView.text = number.toString()
    }

    private fun displayMessage(message: String) {
        val oderSummaryTextView = findViewById<View>(R.id.order_summary_text_view) as TextView
        oderSummaryTextView.text = message
    }

    private fun sendOrder(name: String, orderSummary: String) {
        Log.i("MainActivity.java", "Order for $name has been placed")

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + name)
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}