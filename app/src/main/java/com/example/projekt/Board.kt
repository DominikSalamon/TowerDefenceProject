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

class Coords(val x:Int, val y:Int, val tile:String)

class Board(private var width : Int, private var height : Int, context: Context, extras: Bundle) {
    private var randomGeneneration = extras.getBoolean("randomMap")
    private var xTiles= extras.getInt("xTiles")
    private var yTiles = extras.getInt("yTiles")
    private var tileSize = 100
    private var tiles = mutableListOf<Tile>()
    private var drawables = Drawables(context)

    init{
        if(randomGeneneration){

            val roadTiles = arrayListOf<Coords>()

            roadTiles.add(Coords((0 until xTiles).random(),0,"roadVertical"))

            /*

                0 - tileGrass
                1 - roadVertical
                2 - roadHorizontal
                3 - roadCrossing

            */

            while(true){
                val last = roadTiles.last()

                if(last.x==xTiles-1||last.y==yTiles-1)
                    break

                var avaible = arrayOf<Int>()
                when(last.tile){
                    "roadVertical" -> {
                        avaible = arrayOf(1,3)
                    }
                    "roadHorizontal" -> {
                        avaible = arrayOf(2,3)
                    }
                    "roadCrossing" -> {
                        avaible = arrayOf(1,2,3)
                    }
                }

                when(avaible.random()){
                    1 -> {
                        roadTiles.add(Coords(last.x,last.y+1,"roadVertical"))
                    }
                    2 -> {
                        roadTiles.add(Coords(last.x+1,last.y,"roadHorizontal"))
                    }
                    3 -> {
                        roadTiles.add(Coords(last.x+1,last.x+1,"roadCrossing"))
                    }
                }



            }

            roadTiles.forEach {
                when(it.tile){
                    "roadVertical" -> {
                        tiles.add(RoadVertical(it.x,it.y,tileSize))
                    }
                    "roadHorizontal" -> {
                        tiles.add(RoadHorizontal(it.x,it.y,tileSize))
                    }
                    "roadCrossing" -> {
                        tiles.add(RoadCrossing4(it.x,it.y,tileSize))
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

        Terrain(0,0,width).draw(canvas,drawables)
        tiles.forEach{
            it.draw(canvas,drawables)
        }
    }


}