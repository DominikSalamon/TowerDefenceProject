package com.example.projekt


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat


@SuppressLint("ViewConstructor")
class Game(screenWidth: Int,screenHeight: Int,context: Context, extras: Bundle) : SurfaceView(context),
    SurfaceHolder.Callback {
    private val gameLoop: GameLoop
    private var board : Board
    private val camera : Camera





    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                camera.startPoint(event.x.toInt(),event.y.toInt())
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                camera.move(event.x.toInt(),event.y.toInt())
                return true
            }
        }

        return super.onTouchEvent(event)
    }

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

        board.draw(canvas,camera)

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

        board = Board(context, extras)
        camera = Camera(board.getXWidth()- screenWidth,board.getYHeight()- screenHeight)
    }
}