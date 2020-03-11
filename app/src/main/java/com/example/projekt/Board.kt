package com.example.projekt
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources



class Drawables(context: Context){
    val tileRoadHorizontal = AppCompatResources.getDrawable(context, R.drawable.tile_road_horizontal)
    val tileRoadVertical= AppCompatResources.getDrawable(context, R.drawable.tile_road_vertical)
    val tileRoadCrossing4= AppCompatResources.getDrawable(context, R.drawable.tile_road_crossing4)
    val tileGrass = AppCompatResources.getDrawable(context, R.drawable.tile_grass)
}

class Board(private var width : Int, private var height : Int, context: Context, extras: Bundle) {
    private var randomGeneneration = extras.getBoolean("randomMap")
    private var xTiles= extras.getInt("xTiles")
    private var yTiles = extras.getInt("yTiles")
    private var tileSize = 100
    private var tiles = mutableListOf<Tile>()
    private var drawables = Drawables(context)

    init{
        if(randomGeneneration){
            for(i in 0 until xTiles){
                for(j in 0 until yTiles){
                    if(j==yTiles-1){
                        tiles.add(Terrain(i*tileSize,j*tileSize,tileSize))
                    }
                    else{
                        when((0..3).random()){
                            0 -> tiles.add(Terrain(i*tileSize,j*tileSize,tileSize))
                            1 -> tiles.add(RoadCrossing4(i*tileSize,j*tileSize,tileSize))
                            2 -> tiles.add(RoadHorizontal(i*tileSize,j*tileSize,tileSize))
                            3 -> tiles.add(RoadVertical(i*tileSize,j*tileSize,tileSize))
                        }
                    }
                }
            }
        }
    }

    fun draw(canvas : Canvas){
        val paint = Paint()
        paint.color = Color.rgb(240, 240, 240)

        canvas.drawRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            paint)


        tiles.forEach{
            it.draw(canvas,drawables)
        }
    }


}