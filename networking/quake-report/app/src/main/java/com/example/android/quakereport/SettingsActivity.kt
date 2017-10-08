package com.example.android.quakereport

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import android.preference.PreferenceManager
import android.preference.ListPreference





class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
    }

    class EarthquakePreferenceFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener {
        override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {
            val stringValue = value.toString()
            if (preference is ListPreference) {
                val prefIndex = preference.findIndexOfValue(stringValue)
                if (prefIndex >= 0) {
                    val labels = preference.entries
                    preference.summary = labels[prefIndex]
                }
            } else {
                preference!!.summary = stringValue
            }
            return true
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.settings_main)

            val minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key))
            bindPreferenceSummaryToValue(minMagnitude)

            val orderBy = findPreference(getString(R.string.settings_order_by_key))
            bindPreferenceSummaryToValue(orderBy)
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = this
            val preferences = PreferenceManager.getDefaultSharedPreferences(preference.context)
            val preferenceString = preferences.getString(preference.key, "")
            onPreferenceChange(preference, preferenceString)
        }
    }
}