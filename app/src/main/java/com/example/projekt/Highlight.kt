package com.example.projekt

import android.graphics.Canvas
import kotlin.math.floor

class Highlight(private val mapFactory: MapFactory){
    private var x = 0f
    private var y = 0f
    private var active = false

    fun activate(posX: Float, posY: Float,camera: Camera){
        val trueX = floor((posX-camera.getX())/100/camera.getScale())
        val trueY = floor((posY-camera.getY())/100/camera.getScale())

        if(mapFactory.isTileFree(trueX.toInt(),trueY.toInt())){
            x=trueX
            y=trueY
            active=true
        }else active=false
    }

    fun draw(canvas: Canvas,drawables: Drawables){
        if(active){
            val drawable = drawables.highlight
            drawable?.setBounds(x.toInt()*100,y.toInt()*100,x.toInt()*100+100,y.toInt()*100+100)
            drawable?.draw(canvas)
        }
    }
}