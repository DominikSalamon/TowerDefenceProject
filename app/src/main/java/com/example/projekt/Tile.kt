package com.example.projekt

import android.content.Context
import android.graphics.Canvas
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import java.lang.Exception


class Tile(private var posX: Int, private var posY: Int,private var tileSize: Int){

    fun draw(canvas : Canvas,context : Context){
        try {
            val drawable = getDrawable(context,R.drawable.tile_grass)
            drawable?.setBounds(posX,posY,posX+tileSize,posY+tileSize)
            drawable?.draw(canvas)
        }
        catch (e : Exception){
            e.printStackTrace()
        }
    }
}