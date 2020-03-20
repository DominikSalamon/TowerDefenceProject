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
        tiles = Path(xTiles,yTiles,tileSize,extras).createRandom()
    }

    fun getWidth(): Int{
        return xTiles*tileSize
    }

    fun getHeight(): Int{
        return yTiles*tileSize
    }

    fun draw(canvas : Canvas){

        canvas.drawRGB(200,200,200)

        val drawable = drawables.tileGrass
        drawable?.setBounds(0,0,xTiles*tileSize,yTiles*tileSize)
        drawable?.draw(canvas)


        tiles.forEach{
            it.draw(canvas,drawables)
        }
    }


}