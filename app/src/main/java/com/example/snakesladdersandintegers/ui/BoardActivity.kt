package com.example.snakesladdersandintegers.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snakesladdersandintegers.R
import com.example.snakesladdersandintegers.TotalScoreActivity
import com.example.snakesladdersandintegers.utilities.hideSystemUI
import kotlinx.android.synthetic.main.activity_board.*

class BoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        button_sigma.setOnClickListener {
            val totalScoreActivity = Intent(this@BoardActivity, TotalScoreActivity::class.java)
            startActivity(totalScoreActivity)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus)
            hideSystemUI(window)
    }
}
