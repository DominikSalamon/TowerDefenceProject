package com.example.projekt
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.content.res.AppCompatResources



class Drawables(context: Context){
    val tileRoadHorizontal = AppCompatResources.getDrawable(context, R.drawable.tile_road_horizontal)
    val tileRoadVertical= AppCompatResources.getDrawable(context, R.drawable.tile_road_vertical)
    val tileRoadCrossing4= AppCompatResources.getDrawable(context, R.drawable.tile_road_crossing4)
    val tileGrass = AppCompatResources.getDrawable(context, R.drawable.tile_grass)
}

class Board(private var width : Int, private var height : Int, context: Context, randomGeneneration: Boolean = true) {
    private var xTiles= 16
    private var yTiles = 9
    private var tileWidth : Int
    private var tileHeight : Int
    private var marginLeft : Int
    private var marginTop : Int
    private var tiles = mutableListOf<Tile>()
    private var drawables = Drawables(context)

    fun getStartingPoint() : ()->Unit{
        return {
            val x = (0..xTiles).random()
            val y = yTiles - 1
        }
    }

    init{

        tileWidth = width/xTiles
        tileHeight = height/yTiles

        if(tileHeight>tileWidth)
            tileHeight=tileWidth
        else
            tileWidth=tileHeight

        marginLeft = (width-tileWidth*xTiles)/2
        marginTop = (height-tileHeight*yTiles)/2

        if(randomGeneneration){
            for(i in 0 until xTiles){
                for(j in 0 until yTiles){
                    if(j==yTiles-1){
                        tiles.add(Terrain(i*tileWidth+marginLeft,j*tileHeight+marginTop,tileWidth))
                    }
                    else{
                        when((0..3).random()){
                            0 -> tiles.add(Terrain(i*tileWidth+marginLeft,j*tileHeight+marginTop,tileWidth))
                            1 -> tiles.add(RoadCrossing4(i*tileWidth+marginLeft,j*tileHeight+marginTop,tileWidth))
                            2 -> tiles.add(RoadHorizontal(i*tileWidth+marginLeft,j*tileHeight+marginTop,tileWidth))
                            3 -> tiles.add(RoadVertical(i*tileWidth+marginLeft,j*tileHeight+marginTop,tileWidth))
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