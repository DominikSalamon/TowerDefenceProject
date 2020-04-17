package com.example.projekt

import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder

class GameLoop(private val game: Game, private val surfaceHolder: SurfaceHolder) :
    Thread() {
    private var isRunning = false
    var averageUPS = 0.0
        private set
    var averageFPS = 0.0
        private set

    fun startLoop() {
        Log.d("GameLoop.kt","startLoop")
        if(!isRunning)
            isRunning=true
        start()
    }

    fun stopLoop(){
        Log.d("GameLoop.kt","stopLoop")
        if(isRunning)
            isRunning = false

        try{
            join()
        }catch (e: InterruptedException){
            e.printStackTrace()
        }

    }

    override fun run() {
        Log.d("GameLoop.kt","run")
        super.run()
        var updateCount = 0
        var frameCount = 0
        var startTime: Long
        var elapsedTime: Long
        var sleepTime: Long
        var canvas: Canvas? = null
        startTime = System.currentTimeMillis()
        while (isRunning) {
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    game.update()
                    updateCount++
                    game.draw(canvas)
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                        frameCount++
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            elapsedTime = System.currentTimeMillis() - startTime
            sleepTime = (updateCount * UPS_PERIOD - elapsedTime).toLong()
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                game.update()
                updateCount++
                elapsedTime = System.currentTimeMillis() - startTime
                sleepTime =
                    (updateCount * UPS_PERIOD - elapsedTime).toLong()
            }
            elapsedTime = System.currentTimeMillis() - startTime
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime)
                averageFPS = frameCount / (1E-3 * elapsedTime)
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()
            }
        }
    }

    companion object {
        private const val MAX_UPS = 60.0
        private const val UPS_PERIOD = 1E+3 / MAX_UPS
    }

}