package com.example.android.quakereport

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import java.util.ArrayList
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask


class EarthquakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

        val task = EarthQuakeAsyncTask()
        task.execute(USGS_URL)
    }

    private fun updateUI(earthquakes: ArrayList<EarthQuake>) {

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
        val USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02"
    }

    inner class EarthQuakeAsyncTask : AsyncTask<String, Void, ArrayList<EarthQuake>>() {
        override fun doInBackground(vararg urls: String): ArrayList<EarthQuake>? {
            if (urls.isEmpty() || urls[0] == null) {
                return null
            }
            return QueryUtils.extractEarthquakes(QueryUtils.makeHttpRequest(urls[0]))
        }

        override fun onPostExecute(result: ArrayList<EarthQuake>?) {
            super.onPostExecute(result)
            if (result != null) {
                this@EarthquakeActivity.updateUI(result)
            }
        }
    }}
