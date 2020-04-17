package com.example.projekt

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import kotlin.math.floor

class Highlight(private val mapFactory: MapFactory,drawables: Drawables){
    private var drawableG = drawables.highlight
    private var drawableR = drawables.highlight_red
    private var x = 0f
    private var y = 0f
    private var active = false
    var doubleTapped = false

    fun activate(realX: Int, realY: Int){
        val trueX =  floor(realX/100f)
        val trueY = floor(realY/100f)

        doubleTapped = x==trueX&&y==trueY
        active = mapFactory.isTileFree(trueX.toInt(),trueY.toInt())

        x=trueX
        y=trueY
    }

    fun draw(canvas: Canvas){
        val drawable: Drawable? = if(active)
            drawableG
        else
            drawableR

        drawable?.setBounds(x.toInt()*100,y.toInt()*100,x.toInt()*100+100,y.toInt()*100+100)
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