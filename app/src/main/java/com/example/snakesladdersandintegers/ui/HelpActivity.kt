package com.example.snakesladdersandintegers.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snakesladdersandintegers.R
import com.example.snakesladdersandintegers.utilities.hideSystemUI
import kotlinx.android.synthetic.main.activity_help_activity.*

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_activity)

        button_help_quit.setOnClickListener{
            val menuIntent = Intent(this@HelpActivity, MenuActivity::class.java)
            startActivity(menuIntent)
            finish()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus)
            hideSystemUI(window)
    }
}
