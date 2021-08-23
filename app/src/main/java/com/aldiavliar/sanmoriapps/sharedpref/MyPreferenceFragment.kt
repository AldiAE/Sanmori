package com.aldiavliar.sanmoriapps.sharedpref

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.aldiavliar.sanmoriapps.R

class MyPreferenceFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var NAME: String
    private lateinit var GOL: String
    private lateinit var AGE: String
    private lateinit var NTA: String
    private lateinit var namePreference: EditTextPreference
    private lateinit var golPreference: EditTextPreference
    private lateinit var agePreference: EditTextPreference
    private lateinit var ntaPreference: EditTextPreference

    companion object {
        private const val DEFAULT_VALUE = "Tidak Ada"
    }

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        namePreference.summary = sh.getString(NAME, DEFAULT_VALUE)
        golPreference.summary = sh.getString(GOL, DEFAULT_VALUE)
        agePreference.summary = sh.getString(AGE, DEFAULT_VALUE)
        ntaPreference.summary = sh.getString(NTA, DEFAULT_VALUE)
    }

    private fun init() {
        NAME = resources.getString(R.string.key_name)
        GOL = resources.getString(R.string.key_gol)
        AGE = resources.getString(R.string.key_age)
        NTA = resources.getString(R.string.key_scout)
        namePreference = findPreference<EditTextPreference> (NAME) as EditTextPreference
        golPreference = findPreference<EditTextPreference>(GOL) as EditTextPreference
        agePreference = findPreference<EditTextPreference>(AGE) as EditTextPreference
        ntaPreference = findPreference<EditTextPreference>(NTA) as EditTextPreference
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {

        if (key == NAME) {
            namePreference.summary = sharedPreferences.getString(NAME, DEFAULT_VALUE)
        }
        if (key == GOL) {
            golPreference.summary = sharedPreferences.getString(GOL, DEFAULT_VALUE)
        }
        if (key == AGE) {
            agePreference.summary = sharedPreferences.getString(AGE, DEFAULT_VALUE)
        }
        if (key == NTA) {
            ntaPreference.summary = sharedPreferences.getString(NTA, DEFAULT_VALUE)
        }
    }

}