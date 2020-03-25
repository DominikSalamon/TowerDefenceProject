package com.example.projekt


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat


@SuppressLint("ViewConstructor")
class Game(screenWidth: Int,screenHeight: Int,context: Context, extras: Bundle) : SurfaceView(context),
    SurfaceHolder.Callback {
    private val gameLoop: GameLoop
    //private var board : Board
    //private val camera : Camera

    private val mapka : Mapka




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


        canvas.save()

       // canvas.translate(camera.getX().toFloat(),camera.getY().toFloat())
       // canvas.scale(camera.scale,camera.scale)
        mapka.draw(canvas, Drawables(context))
       // board.draw(canvas)

        canvas.restore()

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

        mapka = Mapka()
        //board = Board(context, extras)
        //camera = Camera(screenWidth, screenHeight,board.getWidth(),board.getHeight())
    }

   /* private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            camera.scale *= detector.scaleFactor

            // Don't let the object get too small or too large.
            camera.scale = 0.1f.coerceAtLeast(camera.scale.coerceAtMost(3.0f))

            invalidate()
            return true
        }
    }

    private val mScaleDetector = ScaleGestureDetector(context, scaleListener)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        mScaleDetector.onTouchEvent(event)

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
    }*/
}