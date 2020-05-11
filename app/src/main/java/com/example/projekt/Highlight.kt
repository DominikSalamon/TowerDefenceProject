package com.example.projekt

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import kotlin.math.floor

class Highlight(private val mapManager: MapManager, drawables: Drawables,private val tileSize: Int){
    private var drawableG = drawables.highlight
    private var drawableR = drawables.highlight_red
    private var x = 0f
    private var y = 0f
    private var active = false
    var doubleTapped = false

    fun activate(realX: Int, realY: Int){
        val trueX =  floor((realX/tileSize).toFloat())
        val trueY = floor((realY/tileSize).toFloat())

        doubleTapped = x==trueX&&y==trueY
        active = mapManager.isTileFree(trueX.toInt(),trueY.toInt())

        x=trueX
        y=trueY
    }

    fun draw(canvas: Canvas){
        val drawable: Drawable? = if(active)
            drawableG
        else
            drawableR

        drawable?.setBounds(x.toInt()*tileSize,y.toInt()*tileSize,x.toInt()*tileSize+tileSize,y.toInt()*tileSize+tileSize)
        drawable?.draw(canvas)
    }

    fun isActive(): Boolean{
        return active
    }

    fun getX(): Float{
        return x
    }
    fun getY(): Float{
        return y
    }

    fun setActive(b: Boolean) {
        active = b
    }
}