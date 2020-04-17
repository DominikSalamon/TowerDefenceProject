package com.example.projekt

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi

class GameActivity : Activity() {
    private lateinit var game: Game
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("GameActivity.kt","onCreate")
        super.onCreate(savedInstanceState)

        hideSystemUI()
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                Handler().postDelayed({
                    hideSystemUI()
                }, 1000)
            }
        }
        val extras = intent.extras!!

        val metrics = DisplayMetrics()

        windowManager.defaultDisplay.getMetrics(metrics)



        game = Game(metrics.widthPixels,metrics.heightPixels,this, extras)
        setContentView(game)
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    override fun onStart() {
        Log.d("GameActivity.kt","onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("GameActivity.kt","onResume")
        super.onResume()
        game
    }


    override fun onPause() {
        Log.d("GameActivity.kt","onPause")
        game.pause()
        super.onPause()
    }

    override fun onStop() {
        Log.d("GameActivity.kt","onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("GameActivity.kt","onDestroy")
        super.onDestroy()
    }


    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}

