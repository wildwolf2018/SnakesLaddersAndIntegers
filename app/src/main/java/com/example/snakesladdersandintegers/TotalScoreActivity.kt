package com.example.snakesladdersandintegers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snakesladdersandintegers.utilities.hideSystemUI

class TotalScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_score)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus)
            hideSystemUI(window)
    }
}
