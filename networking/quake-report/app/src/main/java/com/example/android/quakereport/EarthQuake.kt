package com.example.android.quakereport

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.content.ContextCompat



class EarthQuake(private var magnitude: String = "", location: String = "", date: Long = 0, var url: String = "") {
    val dateDay: String = SimpleDateFormat("LLL dd, yyyy").format(Date(date))
    val dateTime: String = SimpleDateFormat("h:mm a").format(Date(date))

    val locationRelative: String = location.substringBefore(" of ") + " of"
    val locationCity: String = location.substringAfter(" of ")

    val magFormat = getMagnitudeFormat()
    val mag = magnitude.toDouble()

    private fun getMagnitudeFormat(): String {
        val formatter = DecimalFormat("0.0")
        return formatter.format(magnitude.replace(',', '.').toFloat())
    }


}