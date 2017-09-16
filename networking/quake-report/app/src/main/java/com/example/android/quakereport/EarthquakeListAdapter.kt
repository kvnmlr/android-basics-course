package com.example.android.quakereport

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList


class EarthquakeListAdapter (context: Activity, earthquakes: ArrayList<EarthQuake>) : ArrayAdapter<EarthQuake>(context, 0, earthquakes) {

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
        magnitudeTextView.text = currentEarthquake.magnitude

        val locationTextView = listItemView.findViewById(R.id.list_item_location) as TextView
        locationTextView.text = currentEarthquake.location

        val dateTextView = listItemView.findViewById(R.id.list_item_date) as TextView
        dateTextView.text = currentEarthquake.date

        return listItemView
    }

    companion object {
        private val LOG_TAG = "EarthquakeListAdapter"
    }

}