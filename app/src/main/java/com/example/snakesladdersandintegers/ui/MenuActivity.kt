package com.example.snakesladdersandintegers.ui

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.snakesladdersandintegers.R
import com.example.snakesladdersandintegers.utilities.hideSystemUI
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MenuActivity : AppCompatActivity() {

    private lateinit  var navView:NavigationView
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayMetrics = resources.displayMetrics
        val screenHeight = (displayMetrics.heightPixels * 0.8).toInt()
        window.setFlags(WindowManager.LayoutParams.MATCH_PARENT, screenHeight)

        mediaPlayer = MediaPlayer.create(this@MenuActivity,
            R.raw.intro
        )
        mediaPlayer.start()

        val fab: FloatingActionButton = findViewById(R.id.button_play)
        fab.setOnClickListener {
            getUserName(this@MenuActivity)
        }
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        setUpNavigationDrawer()
        openDrawer()

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus)
            hideSystemUI(window)
    }

    private fun openDrawer(){
        GlobalScope.launch(Main){
            delay(3000)
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onResume() {
        super.onResume()
        if(!mediaPlayer.isPlaying) {
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private  fun setUpNavigationDrawer(){
        navView.setNavigationItemSelectedListener {
            menuItem -> menuItem.setChecked(true)
            when(menuItem.itemId){
                R.id.nav_play -> {
                    getUserName(this@MenuActivity)
                }
                R.id.nav_help -> {
                    val helpIntent = Intent(this@MenuActivity, HelpActivity::class.java)
                    startActivity(helpIntent)
                }
                R.id.nav_high_score -> {
                    val scoresIntent = Intent(this@MenuActivity, HighScoresActivity::class.java)
                    startActivity(scoresIntent)
                }
                R.id.nav_resume -> {
                    val boardIntent = Intent(this@MenuActivity, BoardActivity::class.java)
                    startActivity(boardIntent)
                }
                R.id.nav_settings -> {
                   val helpIntent = Intent(this@MenuActivity, SettingsActivity::class.java)
                   startActivity(helpIntent)
                }
            }
            drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener  true
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun getUserName(context: Context){
        val alertDialogBuilder = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.username_dialog, null)

        val editText = view.findViewById<EditText>(R.id.usernameInput)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)

        alertDialogBuilder.setView(view)
        val dialog =alertDialogBuilder.create()
        registerButton.setOnClickListener{
            if(editText.text.isNotEmpty()){
                val sharedPreferences = getSharedPreferences(SHARED_GAME_PREFS, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(USERNAME, editText.text.toString())
                editor.apply()
                Log.d("MenuActivity", "${editText.text}")
                dialog.dismiss()
                val boardIntent = Intent(this@MenuActivity, BoardActivity::class.java)
                startActivity(boardIntent)
            }else{
                Toast.makeText(context, "Please enter a username", Toast.LENGTH_SHORT).show()
            }
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
            val boardIntent = Intent(this@MenuActivity, BoardActivity::class.java)
            startActivity(boardIntent)
        }
        dialog.show()
    }

    companion object{
        private const val SHARED_GAME_PREFS = "Game Data"
        private const val USERNAME = "Username"
        private const val MENU_ACTIVITY = "MenuActivity"
    }
}
