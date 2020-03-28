package com.example.projekt
import android.content.Context
import android.graphics.Canvas

import android.os.Bundle

class Board(context: Context, extras: Bundle) {
    private var xTiles= extras.getInt("xTiles")
    private var yTiles = extras.getInt("yTiles")
    private var drawables = Drawables(context)
    private var tileSize = 100
    private var tiles = arrayListOf<Tile>()


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

        var drawable = drawables.tileGrass
        drawable?.setBounds(0,0,xTiles*tileSize,yTiles*tileSize)
        drawable?.draw(canvas)



        tiles.forEach{
            it.draw(canvas,drawables)
        }

        drawable = drawables.trees
        drawable?.setBounds(1100,1100,1200,1200)
        drawable?.draw(canvas)

        drawable = drawables.mountain
        drawable?.setBounds(500,500,1000,1000)
        drawable?.draw(canvas)

        drawable = drawables.base
        drawable?.setBounds(500,0,600,100)
        drawable?.draw(canvas)

    }


}