package com.example.projekt
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources



class Drawables(context: Context){
    val tileRoadHorizontal = AppCompatResources.getDrawable(context, R.drawable.tile_road_horizontal)
    val tileRoadVertical = AppCompatResources.getDrawable(context, R.drawable.tile_road_vertical)
    val tileRoadCrossing4 = AppCompatResources.getDrawable(context, R.drawable.tile_road_crossing4)
    val tileRoadLeftBottom = AppCompatResources.getDrawable(context, R.drawable.tile_road_left_bottom)
    val tileRoadLeftTop= AppCompatResources.getDrawable(context, R.drawable.tile_road_left_top)
    val tileRoadRightBottom = AppCompatResources.getDrawable(context, R.drawable.tile_road_right_bottom)
    val tileRoadRightTop = AppCompatResources.getDrawable(context, R.drawable.tile_road_right_top)
    val tileGrass = AppCompatResources.getDrawable(context, R.drawable.tile_grass)
}

class Coords(val x:Int, val y:Int, val tile:String)
/*
class Path(private var limitX: Int,private var limitY: Int, private var code: String = "" ){
    class Node(val x:Int, val y:Int, val tile:String)
    private var nodes = mutableListOf<Node>()

    init{
        if(code=="")
            createRandom()
        else
            create()
    }

    fun create(){

    }

    fun createRandom(){
        nodes.add(Node((0 until limitX).random(),0, "tileGrass"))

        for(y in 0 until limitY){
            for(x in 0 until limitX){

            }
        }
    }

    private fun getAvaibleTiles(x:Int, y:Int){
        var avaible = arrayListOf<Path.Node>()

        nodes.forEach {
            if(x+1==it.x&&y==it.y){
                if(it.tile=="roadHorizontal"||it.tile=="roadLeftTop"||it.tile=="roadLeftBottom"||it.tile=="roadCrossing"){



                }
            }

            if(x-1==it.x&&y==it.y){
                if(it.tile=="roadHorizontal"||it.tile=="roadRightTop"||it.tile=="roadRightBottom"||it.tile=="roadCrossing"){



                }
            }

            if(x==it.x&&y+1==it.y){
                if(it.tile=="roadVertical"||it.tile=="roadLeftBottom"||it.tile=="roadRightBottom"||it.tile=="roadCrossing"){



                }
            }

            if(x==it.x&&y-1==it.y){
                if(it.tile=="roadVertical"||it.tile=="roadLeftTop"||it.tile=="roadRightTop"||it.tile=="roadCrossing"){



                }
            }
        }

        return avaible
    }

    fun isOccupied(x:Int,y:Int): Boolean {
        nodes.forEach{
            if(it.x==x&&it.y==y) return true
        }
        return false
    }
}
*/
class Board(private var width : Int, private var height : Int, context: Context, extras: Bundle) {
    private var randomGeneneration = extras.getBoolean("randomMap")
    private var xTiles= extras.getInt("xTiles")
    private var yTiles = extras.getInt("yTiles")
    private var tileSize = 100
    private var tiles = mutableListOf<Tile>()
    private var drawables = Drawables(context)

    init{
        if(randomGeneneration){

            /*

                0 - tileGrass
                1 - roadVertical
                2 - roadHorizontal
                3 - roadCrossing

            */

            val roadTiles = arrayListOf<Coords>()
            val nextTiles = arrayListOf<Coords>()

            val startX = (0 until xTiles).random()

            roadTiles.add(Coords(startX,0,"roadVertical"))
            nextTiles.add(Coords(startX,1,"unset"))



            var last: Coords
            var prevLast: Coords
            while(true){
                last = roadTiles.last()

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

                prevLast = last

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