package com.example.snakesladdersandintegers.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.example.snakesladdersandintegers.R
import com.example.snakesladdersandintegers.utilities.hideSystemUI
import kotlinx.android.synthetic.main.settings_activity.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.settings,
                SettingsFragment()
            )
            .commit()
        setSupportActionBar(toolbar_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_settings.setNavigationOnClickListener{
            backButtonPressed()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus)
            hideSystemUI(window)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        backButtonPressed()
    }

    private fun backButtonPressed(){
        val menuIntent = Intent(this@SettingsActivity, MenuActivity::class.java)
        menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(menuIntent)
        finish()
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}