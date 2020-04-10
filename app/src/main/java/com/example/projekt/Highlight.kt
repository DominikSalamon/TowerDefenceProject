package com.example.projekt

import android.graphics.Canvas
import kotlin.math.floor

class Highlight(private val mapFactory: MapFactory){
    private var x = 0f
    private var y = 0f
    private var mapWidth: Int = mapFactory.getMapX()
    private var mapHeight: Int = mapFactory.getMapY()


    fun activate(posX: Float, posY: Float,camera: Camera){
        var trueX = floor((posX-camera.getX())/100/camera.getScale())
        var trueY = floor((posY-camera.getY())/100/camera.getScale())

        if(trueX<0) trueX=0f
        else if(trueX>=mapWidth) trueX=mapWidth-1f

        if(trueY<0) trueY=0f
        else if(trueY>=mapHeight) trueY=mapHeight-1f

        if(mapFactory.isTileFree(trueX.toInt(),trueY.toInt())){
            x=trueX
            y=trueY
        }
    }

    fun draw(canvas: Canvas,drawables: Drawables){
        val drawable =  drawables.highlight
        drawable?.setBounds(x.toInt()*100,y.toInt()*100,x.toInt()*100+100,y.toInt()*100+100)
        drawable?.draw(canvas)
    }
}