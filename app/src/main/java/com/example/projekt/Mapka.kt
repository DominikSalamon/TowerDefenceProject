package com.example.projekt

import android.graphics.Canvas
import android.graphics.drawable.Drawable
class Mapka{
    private val tileSize = 50
    private val tab =
        arrayOf(intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0))

    private fun getDrawable(i: Int, drawables: Drawables): Drawable? {
        return when(i){
            0 -> drawables.tileGrass
            else -> drawables.tileRoad
        }

    }

    fun draw(canvas: Canvas, drawables: Drawables){
        for(i in 0 until 11){
            for(j in 0 until 15){
                val drawable = getDrawable(tab[i][j], drawables)
                drawable?.setBounds(j*tileSize,i*tileSize,(j+1)*tileSize,(i+1)*tileSize)
                drawable?.draw(canvas)
            }
        }
    }



}