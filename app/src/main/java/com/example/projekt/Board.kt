package com.example.projekt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint



class Board(private var width : Int, private var height : Int, randomGeneneration: Boolean = true) {
    private var xTiles= 16
    private var yTiles = 9
    private var tileWidth : Int
    private var tileHeight : Int
    private var marginLeft : Int
    private var marginTop : Int
    private var tiles = mutableListOf<Tile>()

    init{
        tileWidth = width/xTiles
        tileHeight = tileWidth
        marginLeft = (width%xTiles)/2
        marginTop = (height-yTiles*tileHeight)/2

        if(randomGeneneration){
            for(i in 0 until xTiles){
                for(j in 0 until yTiles){
                    tiles.add(Tile(i*tileWidth+marginLeft,j*tileHeight+marginTop,tileWidth))
                }
            }
        }
    }

    fun draw(canvas : Canvas,context : Context){
        val paint = Paint()
        paint.color = Color.rgb(200, 0, 0)

        canvas.drawRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            paint)

        paint.color = Color.rgb(0,200, 0)
        canvas.drawRect(
            marginLeft.toFloat(),
            marginTop.toFloat(),
            (tileWidth*xTiles+marginLeft).toFloat(),
            (tileHeight*yTiles+marginTop).toFloat(),
            paint)

        tiles.forEach{
            it.draw(canvas,context)
        }
    }


}