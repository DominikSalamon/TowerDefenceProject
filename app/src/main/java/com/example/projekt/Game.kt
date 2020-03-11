package com.example.projekt


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat


@SuppressLint("ViewConstructor")
class Game(context: Context, private var extras: Bundle) : SurfaceView(context),
    SurfaceHolder.Callback {
    private val gameLoop: GameLoop
    private lateinit var board : Board
    private var isBoard : Boolean = false


    override fun surfaceCreated(holder: SurfaceHolder) {
        gameLoop.startLoop()

    }

    override fun surfaceChanged(
        holder: SurfaceHolder,
        format: Int,
        width: Int,
        height: Int
    ) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {}
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if(!isBoard) {
            board = Board(width, height, context, extras)
            isBoard=true
        }

        board.draw(canvas)

        drawUPS(canvas)
        drawFPS(canvas)
    }

    private fun drawUPS(canvas: Canvas) {
        val averageUPS = gameLoop.averageUPS.toInt().toString()
        val paint = Paint()
        paint.flags = Paint.ANTI_ALIAS_FLAG
        val color = ContextCompat.getColor(context, R.color.colorDark)
        paint.color = color
        paint.textSize = 40f
        canvas.drawText("UPS: $averageUPS", 20f, 50f, paint)
    }

    private fun drawFPS(canvas: Canvas) {
        val averageFPS = gameLoop.averageFPS.toInt().toString()
        val paint = Paint()
        paint.flags = Paint.ANTI_ALIAS_FLAG
        val color = ContextCompat.getColor(context, R.color.colorDark)
        paint.color = color
        paint.textSize = 40f
        canvas.drawText("FPS: $averageFPS", 20f, 110f, paint)
    }

    fun update() {

    }

    init {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        gameLoop = GameLoop(this, surfaceHolder)
        isFocusable = true
    }
}