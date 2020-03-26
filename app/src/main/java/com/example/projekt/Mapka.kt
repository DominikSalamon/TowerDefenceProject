package com.example.projekt

import android.graphics.Canvas
import android.graphics.drawable.Drawable
class Mapka{
    private val tileSize = 100
    private val map1 =
        arrayOf(intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0))
    private val map2 =
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0),
            intArrayOf(1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        )
    private val map3 =
        arrayOf(
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0)
        )


    private fun getDrawable(i: Int, drawables: Drawables): Drawable? {
        return when(i){
            0 -> drawables.tileGrass
            else -> drawables.tileRoad
        }

    }

    fun draw(canvas: Canvas, drawables: Drawables){
        if(MenuPlay().id_map == 1){
        for(i in 0 until 11) {
            for (j in 0 until 15) {
                val drawable = getDrawable(map1[i][j], drawables)
                drawable?.setBounds(j * tileSize, i * tileSize, (j + 1) * tileSize, (i + 1) * tileSize
                )
                drawable?.draw(canvas)
                }
            }
        }
        else if(MenuPlay().id_map == 2) {
            for(i in 0 until 11) {
                for (j in 0 until 15) {
                    val drawable = getDrawable(map2[i][j], drawables)
                    drawable?.setBounds(j * tileSize, i * tileSize, (j + 1) * tileSize, (i + 1) * tileSize
                    )
                    drawable?.draw(canvas)
                }
            }
        }
        else if(MenuPlay().id_map == 3) {
            for(i in 0 until 11) {
                for (j in 0 until 15) {
                    val drawable = getDrawable(map3[i][j], drawables)
                    drawable?.setBounds(j * tileSize, i * tileSize, (j + 1) * tileSize, (i + 1) * tileSize
                    )
                    drawable?.draw(canvas)
                }
            }
        }
    }



}