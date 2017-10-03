package com.example.android.quakereport

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import android.graphics.drawable.GradientDrawable


class EarthquakeListAdapter (context: Activity, earthquakes: List<EarthQuake>) : ArrayAdapter<EarthQuake>(context, 0, earthquakes) {

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     * list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {listItemView = LayoutInflater.from(context).inflate(R.layout.overview_list_item, parent, false)
        }

        val currentEarthquake = getItem(position)

        val magnitudeTextView = listItemView!!.findViewById(R.id.list_item_magnitude) as TextView
        magnitudeTextView.text = currentEarthquake.magFormat

        val magnitudeCircle = magnitudeTextView.background as GradientDrawable
        magnitudeCircle.setColor(getMagnitudeColor(currentEarthquake.mag))

        val locationRelativeTextView = listItemView.findViewById(R.id.list_item_location_relative) as TextView
        locationRelativeTextView.text = currentEarthquake.locationRelative

        val locationCityTextView = listItemView.findViewById(R.id.list_item_location_city) as TextView
        locationCityTextView.text = currentEarthquake.locationCity

        val dateTextView = listItemView.findViewById(R.id.list_item_date) as TextView
        dateTextView.text = currentEarthquake.dateDay

        val timeTextView = listItemView.findViewById(R.id.list_item_time) as TextView
        timeTextView.text = currentEarthquake.dateTime

        return listItemView
    }

    companion object {
        private val LOG_TAG = "EarthquakeListAdapter"
    }

    private fun getMagnitudeColor(magnitude: Double): Int {
        val magnitudeColorResourceId: Int
        val magnitudeFloor = Math.floor(magnitude).toInt()
        magnitudeColorResourceId = when (magnitudeFloor) {
            0, 1 -> R.color.magnitude1
            2 -> R.color.magnitude2
            3 -> R.color.magnitude3
            4 -> R.color.magnitude4
            5 -> R.color.magnitude5
            6 -> R.color.magnitude6
            7 -> R.color.magnitude7
            8 -> R.color.magnitude8
            9 -> R.color.magnitude9
            else -> R.color.magnitude10plus
        }
        return ContextCompat.getColor(context, magnitudeColorResourceId)
    }

}