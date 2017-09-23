package com.example.android.quakereport

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import java.util.ArrayList
import android.content.Intent
import android.net.Uri
import java.net.URL


class EarthquakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

        // Create a fake list of earthquake locations.
        /*
        val earthquakes = ArrayList<EarthQuake>()
        earthquakes.add(EarthQuake("7.2", "San Francisco", "Feb 2, 2017"))
        earthquakes.add(EarthQuake("5.5","London","Feb 4, 2017"))
        earthquakes.add(EarthQuake("6.3","Tokyo","Feb 5, 2017"))
        earthquakes.add(EarthQuake("3.5","Mexico City","Feb 6, 2017"))
        earthquakes.add(EarthQuake("4.6","Moscow","Feb 7, 2017"))
        earthquakes.add(EarthQuake("2.7","Rio de Janeiro","Feb 8, 2017"))
        earthquakes.add(EarthQuake("1.1","Paris","Feb 11, 2017"))*/

        val earthquakes = QueryUtils.extractEarthquakes(QueryUtils.makeHttpRequest(URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02")))

        // Find a reference to the {@link ListView} in the layout
        val earthquakeListView = findViewById(R.id.list) as ListView?
        earthquakeListView?.setOnItemClickListener({ _, _, i: Int, l: Long ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(earthquakes[i].url)
            startActivity(intent)
        })

        // Create a new {@link ArrayAdapter} of earthquakes
        val adapter = EarthquakeListAdapter(this, earthquakes)


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView!!.adapter = adapter
    }

    companion object {
        val LOG_TAG = "EarthquakeActivity"
    }
}
