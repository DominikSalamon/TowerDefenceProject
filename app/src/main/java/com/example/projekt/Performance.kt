package com.example.projekt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat

class Performance(context: Context,private var gameLoop: GameLoop){
    private var paint: Paint = Paint()

    init{
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.color = ContextCompat.getColor(context, R.color.colorDark)
        paint.textSize = 40f
    }

    fun draw(canvas: Canvas){
        drawUPS(canvas)
        drawFPS(canvas)
    }

    private fun drawUPS(canvas: Canvas) {
        val averageUPS = gameLoop.averageUPS.toInt().toString()
        canvas.drawText("UPS: $averageUPS", 20f, 50f, paint)
    }

    private fun drawFPS(canvas: Canvas) {
        val averageFPS = gameLoop.averageFPS.toInt().toString()
        canvas.drawText("FPS: $averageFPS", 20f, 110f, paint)
    }
}