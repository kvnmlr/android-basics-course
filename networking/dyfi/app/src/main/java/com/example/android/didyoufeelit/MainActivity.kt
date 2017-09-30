/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.didyoufeelit

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

/**
 * Displays the perceived strength of a single earthquake event based on responses from people who
 * felt the earthquake.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Perform the HTTP request for earthquake data and process the response.
        val task = EarthQuakeAsyncTask()
        task.execute(USGS_REQUEST_URL)

        // Update the information displayed to the user.
    }

    /**
     * Update the UI with the given earthquake information.
     */
    private fun updateUi(earthquake: Event) {
        val titleTextView = findViewById(R.id.title) as TextView?
        titleTextView!!.text = earthquake.title

        val tsunamiTextView = findViewById(R.id.number_of_people) as TextView?
        tsunamiTextView!!.text = getString(R.string.num_people_felt_it, earthquake.numOfPeople)

        val magnitudeTextView = findViewById(R.id.perceived_magnitude) as TextView?
        magnitudeTextView!!.text = earthquake.perceivedStrength
    }

    companion object {

        /** URL for earthquake data from the USGS dataset  */
        private val USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5"
    }

    inner class EarthQuakeAsyncTask : AsyncTask<String, Void, Event>() {
        override fun doInBackground(vararg urls: String?): Event? {
            if (urls.isEmpty() || urls[0] == null) {
                return null
            }
            return Utils.fetchEarthquakeData(urls[0])
        }

        override fun onPostExecute(result: Event?) {
            super.onPostExecute(result)
            if (result != null) {
                this@MainActivity.updateUi(result)
            }
        }
    }
}
