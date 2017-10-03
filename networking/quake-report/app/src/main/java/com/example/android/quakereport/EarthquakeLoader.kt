package com.example.android.quakereport

import android.content.AsyncTaskLoader
import android.content.Context
import android.util.Log

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
class EarthquakeLoader(context: Context?, private val mUrl: String) : AsyncTaskLoader<List<EarthQuake>>(context) {

    override fun onStartLoading() {
        Log.i(LOG_TAG, "onStartLoading()")
        forceLoad()
    }

    /**
     * This is on a background thread.
     */
    override fun loadInBackground(): List<EarthQuake>? {
        Log.i(LOG_TAG, "loadInBackground()")

        return if (mUrl == null) {
            null
        } else QueryUtils.extractEarthquakes(QueryUtils.makeHttpRequest(mUrl))
    }

    companion object {

        /** Tag for log messages  */
        private val LOG_TAG = EarthquakeLoader::class.java.name
    }
}