package com.example.projekt
import android.content.Context
import android.graphics.Canvas

import android.os.Bundle

class Board(context: Context, extras: Bundle) {
    private var xTiles= extras.getInt("xTiles")
    private var yTiles = extras.getInt("yTiles")
    private var tileSize = 100
    private var tiles = arrayListOf<Tile>()
    private var drawables = Drawables(context)

    init{
        tiles = Path(xTiles,yTiles,tileSize).createRandom()
    }

    fun getXWidth(): Int{
        return xTiles*tileSize
    }

    fun getYHeight(): Int{
        return yTiles*tileSize
    }

    fun draw(canvas : Canvas, camera: Camera){



        canvas.drawRGB(200,200,200)

        val drawable = drawables.tileGrass
        drawable?.setBounds(camera.getX(),camera.getY(),camera.getX()+xTiles*tileSize,camera.getY()+yTiles*tileSize)
        drawable?.draw(canvas)


        tiles.forEach{
            it.draw(canvas,drawables,camera)
        }
    }


}