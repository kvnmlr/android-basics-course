package com.example.android.quakereport

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.content.Intent
import android.net.Uri
import android.app.LoaderManager
import android.content.Loader
import android.util.Log
import android.widget.TextView


class EarthquakeActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<EarthQuake>> {
    private var mAdapter: EarthquakeListAdapter? = null
    private lateinit var mEarthquakeListView: ListView
    private lateinit var mEmptyStateTextView: TextView
    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<List<EarthQuake>> {
        Log.i(LOG_TAG, "onCreateLoader()")
        return EarthquakeLoader(this, USGS_URL)
    }

    override fun onLoaderReset(p0: Loader<List<EarthQuake>>?) {
        Log.i(LOG_TAG, "onLoaderReset()")
        mAdapter!!.clear()
    }

    override fun onLoadFinished(p0: Loader<List<EarthQuake>>?, result: List<EarthQuake>?) {
        Log.i(LOG_TAG, "onLoadFinished()")
        mEmptyStateTextView.setText(R.string.no_earthquakes)

        if (result != null) {
            this@EarthquakeActivity.updateUI(result)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

        mEarthquakeListView = findViewById(R.id.list) as ListView
        mEmptyStateTextView = findViewById(R.id.empty_view) as TextView

        mEarthquakeListView.emptyView = mEmptyStateTextView

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this)
    }

    private fun updateUI(earthquakes: List<EarthQuake>) {
        // Find a reference to the {@link ListView} in the layout
        mEarthquakeListView.setOnItemClickListener({ _, _, i: Int, _: Long ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(earthquakes[i].url)
            startActivity(intent)
        })

        mAdapter = EarthquakeListAdapter(this, earthquakes)

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mEarthquakeListView.adapter = mAdapter
    }

    companion object {
        private val LOG_TAG = EarthquakeActivity::class.java.name
        private val USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10"
        private val EARTHQUAKE_LOADER_ID = 1
    }
}
